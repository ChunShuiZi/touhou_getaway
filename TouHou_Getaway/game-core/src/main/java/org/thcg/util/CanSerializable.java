package org.thcg.util;

import lombok.NonNull;

import java.io.OutputStream;

/**
 * @author Severle
 * @date 2024-01-22 15:58:16
 * @description
 */
public interface CanSerializable {
    boolean serialize(@NonNull OutputStream os);

    Resources.SerializeType type();
}
