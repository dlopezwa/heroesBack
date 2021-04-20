package com.form.heroesBack.export.interfaces;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExportStrategy {
    public <T> ByteArrayInputStream export(List<T> listElements);
}
