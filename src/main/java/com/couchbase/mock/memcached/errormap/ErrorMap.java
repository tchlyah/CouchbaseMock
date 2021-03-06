/*
 * Copyright 2017 Couchbase, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.couchbase.mock.memcached.errormap;

import com.couchbase.mock.memcached.protocol.ErrorCode;
import com.couchbase.mock.util.ReaderUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mnunberg on 4/12/17.
 */
public class ErrorMap {
    // Opcodes in the existing error map
    public final static ErrorMap DEFAULT_ERRMAP;
    static {
        try {
            String tmp = ReaderUtils.fromResource("errmap/errmap_v1.json");
            DEFAULT_ERRMAP = ErrorMap.parse(tmp);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private int version;
    private int revision;
    private Map<String, ErrorMapEntry> errors;

    public static ErrorMap parse(String json) {
        return new Gson().fromJson(json, ErrorMap.class);
    }

    public int getVersion() {
        return version;
    }

    public int getRevision() {
        return revision;
    }

    public ErrorMapEntry getErrorEntry(int code) {
        code &= code & 0xffff;
        return errors.get(Integer.toHexString(code));
    }

    public ErrorMapEntry getErrorEntry(ErrorCode code) {
        return getErrorEntry(code.value());
    }
}
