/*
 * Copyright 2018 Couchbase, Inc.
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

package com.couchbase.mock.client;

import com.couchbase.mock.memcached.CompressionMode;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.util.Collection;

public class SetCompressionRequest extends MockRequest {
    public SetCompressionRequest(CompressionMode mode) {
        super();
        setName("set_compression");
        payload.put("mode", mode.value());
    }

    public SetCompressionRequest(CompressionMode mode, String bucket, Collection<Integer> servers) {
        this(mode);

        payload.put("bucket", bucket);
        JsonArray arr = new JsonArray();

        for (int ix : servers) {
            arr.add(new JsonPrimitive(ix));
        }

        payload.put("servers", arr);
    }
}