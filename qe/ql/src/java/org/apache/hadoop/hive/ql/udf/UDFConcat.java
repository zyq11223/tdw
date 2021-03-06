/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.description;
import org.apache.hadoop.io.Text;

@description(name = "concat", value = "_FUNC_(str1, str2) - returns the concatenation of str1 and str2", extended = "Returns NULL if any argument is NULL.\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('abc', 'def') FROM src LIMIT 1;\n"
    + "  'abcdef'")
public class UDFConcat extends UDF {

  public UDFConcat() {
  }

  private final Text text = new Text();

  public Text evaluate(Text a, Text b) {
    if (a == null || b == null) {
      return null;
    }

    text.clear();
    text.set(a);
    text.append(b.getBytes(), 0, b.getLength());
    return text;
  }

  public Text evaluate(Text a, Text b, Text c, Text... args) {
    if (a == null || b == null || c == null) {
      return null;
    }

    text.clear();
    text.set(a);
    text.append(b.getBytes(), 0, b.getLength());
    text.append(c.getBytes(), 0, c.getLength());

    for (Text arg : args) {
      if (arg == null) {
        return null;
      }
      text.append(arg.getBytes(), 0, arg.getLength());

    }

    return text;
  }
}
