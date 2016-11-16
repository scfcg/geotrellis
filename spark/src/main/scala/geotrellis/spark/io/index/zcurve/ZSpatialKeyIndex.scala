/*
 * Copyright 2016 Azavea
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

package geotrellis.spark.io.index.zcurve

import geotrellis.spark._
import geotrellis.spark.io.index.KeyIndex
import geotrellis.spark.io.index.zcurve._

class ZSpatialKeyIndex(val keyBounds: KeyBounds[SpatialKey]) extends KeyIndex[SpatialKey] {
  private def toZ(key: SpatialKey): Z2 = Z2(key.col, key.row)

  def toIndex(key: SpatialKey): Long = toZ(key).z

  def indexRanges(keyRange: (SpatialKey, SpatialKey)): Seq[(Long, Long)] =
    Z2.zranges(toZ(keyRange._1), toZ(keyRange._2))
}
