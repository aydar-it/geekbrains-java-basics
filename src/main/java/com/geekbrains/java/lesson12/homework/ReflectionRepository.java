package com.geekbrains.java.lesson12.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionRepository<T> {

    private final Class<T> tClass;
    private final Connection conn;
    private final List<Field> fields;

    public ReflectionRepository(Class<T> tClass, Connection conn) {
        if (!isCorrect(tClass)) {
            throw new RuntimeException("Incorrect class");
        }
        this.tClass = tClass;
        this.conn = conn;
        this.fields = getObjectFields();
    }

    public T add(T obj) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(String.format("INSERT INTO %s (%s) VALUES (%s);",
                    getTableName(), getColumns(), getObjValues(obj)));
            List<T> all = this.getAll();
            return all.get(all.size() - 1);
        }
    }

    public T get(long id) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s WHERE %s = %d;",
                getTableName(), getIdField().getName(), id))) {
            if (!rs.next()) {
                return null;
            }
            return createObjectFromResultSet(rs);
        }
    }

    public List<T> getAll() throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s ORDER BY %s;",
                getTableName(), getIdField().getName()))) {
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(createObjectFromResultSet(rs));
            }
            return result;
        }
    }

    public void remove(long id) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(String.format("DELETE FROM %s WHERE %s = %d;",
                    getTableName(), getIdField().getName(), id));
        }
    }

    public void removeAll() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(String.format("DELETE FROM %s;", getTableName()));
        }
    }

    private boolean isCorrect(Class<T> tClass) {
        long count = Arrays.stream(tClass.getDeclaredFields())
                .filter(e -> e.getAnnotation(DbId.class) != null)
                .count();
        if (count != 1 || tClass.getAnnotation(DbTable.class) == null) {
            return false;
        }
        return true;
    }

    private String getColumns() {
        return fields.stream()
                .map(Field::getName)
                .collect(Collectors.joining(", "));
    }

    private Field getIdField() {
        return Arrays.stream(tClass.getDeclaredFields())
                .filter(e -> e.getAnnotation(DbId.class) != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No ID field"));
    }

    private T createObjectFromResultSet(ResultSet rs) {
        try {
            T result = tClass.getConstructor().newInstance();
            Field idField = getIdField();
            getMethod("set", idField, idField.getType()).invoke(result, rs.getLong(1));
            int col = 2;
            for (Field field : fields) {
                Method method = getMethod("set", field, field.getType());
                if (field.getType().equals(String.class)) {
                    method.invoke(result, rs.getString(col));
                } else if (field.getType().equals(Integer.class)) {
                    method.invoke(result, rs.getInt(col));
                } else {
                    throw new IllegalStateException("Unexpected value: " + field.getType());
                }
                col++;
            }
            return result;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Field> getObjectFields() {
        return Arrays.stream(tClass.getDeclaredFields())
                .filter(e -> e.getAnnotation(DbColumn.class) != null)
                .collect(Collectors.toList());
    }

    private Object getObjValues(T obj) {
        return fields.stream()
                .map(e -> this.fieldToString(e, obj))
                .collect(Collectors.joining(", "));
    }

    private String getTableName() {
        return tClass.getAnnotation(DbTable.class).name();
    }

    private String fieldToString(Field field, T obj) {
        try {
            Method method = getMethod("get", field);
            if (method.getReturnType().equals(String.class)) {
                return "\"" + method.invoke(obj) + "\"";
            }
            return method.invoke(obj).toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getMethod(String str, Field field, Class... args) throws NoSuchMethodException {
        return tClass.getMethod(String.format("%s%c%s", str, Character.toUpperCase(field.getName().charAt(0)),
                field.getName().substring(1)), args);
    }

}
