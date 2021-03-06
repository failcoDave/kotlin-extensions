/*
 * Copyright 2016 David Hull
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.failco.kotlections

import java.util.*


/**
 * Builds and populates a performance-friendly [Map] based on Java's [EnumMap],
 * Uses the given [valueOf] function determine each Enum's mapped value.
 * */
inline fun <ENUM : kotlin.Enum<ENUM>, VAL>
        buildEnumMap(clazz: Class<ENUM>, valueOf: (ENUM) -> VAL): Map<ENUM, VAL> = buildMutableEnumMap(clazz, valueOf)

/**
 * Builds and populates a performance-friendly [MutableMap] based on Java's [EnumMap],
 * Uses the given [initialValueOf] function determine each Enum's initial mapped value.
 * */
inline fun <ENUM : kotlin.Enum<ENUM>, VAL>
        buildMutableEnumMap(clazz: Class<ENUM>, initialValueOf: (ENUM) -> VAL): MutableMap<ENUM, VAL> {

    // build an empty enumMap, iterate each enum constant and pass the map along to the next folder.
    return clazz.enumConstants.fold(EnumMap<ENUM, VAL>(clazz)) {
        map, key -> map[key] = initialValueOf(key)
        return@fold map
    }
}
