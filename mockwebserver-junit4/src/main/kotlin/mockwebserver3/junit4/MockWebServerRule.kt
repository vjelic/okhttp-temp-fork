/*
 * Copyright (C) 2020 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mockwebserver3.junit4

import java.io.IOException
import mockwebserver3.MockWebServer
import org.junit.rules.ExternalResource

/**
 * Runs MockWebServer for the duration of a single test method.
 *
 * In Java JUnit 4 tests (ie. tests annotated `@org.junit.Test`), use this by defining a field with
 * the `@Rule` annotation:
 *
 * ```java
 * @Rule public final MockWebServerRule serverRule = new MockWebServerRule();
 * ```
 *
 * For Kotlin the `@JvmField` annotation is also necessary:
 *
 * ```kotlin
 * @JvmField @Rule val serverRule = MockWebServerRule()
 * ```
 */
class MockWebServerRule : ExternalResource() {
  val server: MockWebServer = MockWebServer()

  override fun before() {
    try {
      server.start()
    } catch (e: IOException) {
      throw RuntimeException(e)
    }
  }

  override fun after() {
    server.close()
  }
}
