package net.jidb.to.base.extension

import net.minecraft.nbt.*
import net.minecraft.network.chat.Component
import java.util.*

fun CompoundTag.getStringOrNull(key: String) = if (this.contains(key, Tag.TAG_STRING.toInt())) this.getString(key) else null
fun CompoundTag.getBooleanOrNull(key: String) = if (this.contains(key, Tag.TAG_BYTE.toInt())) this.getBoolean(key) else null
fun CompoundTag.getByteOrNull(key: String) = if (this.contains(key, Tag.TAG_BYTE.toInt())) this.getByte(key) else null
fun CompoundTag.getShortOrNull(key: String) = if (this.contains(key, Tag.TAG_SHORT.toInt())) this.getShort(key) else null
fun CompoundTag.getIntOrNull(key: String) = if (this.contains(key, Tag.TAG_INT.toInt())) this.getInt(key) else null
fun CompoundTag.getLongOrNull(key: String) = if (this.contains(key, Tag.TAG_LONG.toInt())) this.getLong(key) else null
fun CompoundTag.getFloatOrNull(key: String) = if (this.contains(key, Tag.TAG_FLOAT.toInt())) this.getFloat(key) else null
fun CompoundTag.getDoubleOrNull(key: String) = if (this.contains(key, Tag.TAG_DOUBLE.toInt())) this.getDouble(key) else null
fun CompoundTag.getListOrNull(key: String, type: Int) = if (this.contains(key, Tag.TAG_LIST.toInt())) this.getList(key, type) else null
fun CompoundTag.getCompoundOrNull(key: String) = if (this.contains(key, Tag.TAG_COMPOUND.toInt())) this.getCompound(key) else null
fun CompoundTag.getUuidOrNull(key: String) = if (this.contains(key, Tag.TAG_INT_ARRAY.toInt())) this.getUUID(key) else null

fun CompoundTag.putStringOrRemove(key: String, value: String?) = if (value != null) this.putString(key, value) else this.remove(key)
fun CompoundTag.putBooleanOrRemove(key: String, value: Boolean?) = if (value != null) this.putBoolean(key, value) else this.remove(key)
fun CompoundTag.putByteOrRemove(key: String, value: Byte?) = if (value != null) this.putByte(key, value) else this.remove(key)
fun CompoundTag.putShortOrRemove(key: String, value: Short?) = if (value != null) this.putShort(key, value) else this.remove(key)
fun CompoundTag.putIntOrRemove(key: String, value: Int?) = if (value != null) this.putInt(key, value) else this.remove(key)
fun CompoundTag.putLongOrRemove(key: String, value: Long?) = if (value != null) this.putLong(key, value) else this.remove(key)
fun CompoundTag.putFloatOrRemove(key: String, value: Float?) = if (value != null) this.putFloat(key, value) else this.remove(key)
fun CompoundTag.putDoubleOrRemove(key: String, value: Double?) = if (value != null) this.putDouble(key, value) else this.remove(key)
fun CompoundTag.putListOrRemove(key: String, value: ListTag?) { if (value != null) this.put(key, value) else this.remove(key) }
fun CompoundTag.putCompoundOrRemove(key: String, value: CompoundTag?) { if (value != null) this.put(key, value) else this.remove(key) }
fun CompoundTag.putUuidOrRemove(key: String, value: UUID?) = if (value != null) this.putUUID(key, value) else this.remove(key)

fun <T> CompoundTag.getCompoundObject(key: String, read: (nbt: CompoundTag) -> T) = read(this.getCompound(key))
fun <T> CompoundTag.getCompoundObjectOrNull(key: String, read: (nbt: CompoundTag) -> T?) = if (this.contains(key, Tag.TAG_COMPOUND.toInt())) read(this.getCompound(key)) else null
fun <T> CompoundTag.putCompoundObject(key: String, obj: T, write: (obj: T) -> CompoundTag): CompoundTag {
    val nbt = write(obj)
    put(key, nbt)
    return nbt
}
fun <T> CompoundTag.putCompoundObjectOrRemove(key: String, obj: T?, write: (obj: T) -> CompoundTag) { if (obj != null) this.putCompoundObject(key, obj, write) else this.remove(key) }
fun <T> CompoundTag.getStringObject(key: String, read: (string: String) -> T) = read(this.getString(key))
fun <T> CompoundTag.getStringObjectOrNull(key: String, read: (string: String) -> T?) = if (this.contains(key, Tag.TAG_STRING.toInt())) read(this.getString(key)) else null
fun <T> CompoundTag.putStringObject(key: String, obj: T, write: (obj: T) -> String): String {
    val str = write(obj)
    putString(key, str)
    return str
}
fun <T> CompoundTag.putStringObjectOrRemove(key: String, obj: T?, write: (obj: T) -> String) { if (obj != null) this.putStringObject(key, obj, write) else this.remove(key) }

fun CompoundTag.getComponent(key: String) = getStringObject(key, Component.Serializer::fromJson)
fun CompoundTag.getComponentOrNull(key: String) = getStringObjectOrNull(key, Component.Serializer::fromJson)
fun CompoundTag.putComponent(key: String, component: Component) = putStringObject(key, component, Component.Serializer::toJson)
fun CompoundTag.putComponentOrRemove(key: String, value: Component?) { if (value != null) this.putComponent(key, value) else this.remove(key) }
fun CompoundTag.getStringUuid(key: String) = getStringObject(key, UUID::fromString)
fun CompoundTag.getStringUuidOrNull(key: String) = getStringObjectOrNull(key) {
    try {
        UUID.fromString(it)
    } catch (e: IllegalArgumentException) {
        null
    }
}
fun CompoundTag.putStringUuid(key: String, uuid: UUID) = putStringObject(key, uuid, UUID::toString)
fun CompoundTag.putStringUuidOrRemove(key: String, value: UUID?) { if (value != null) this.putStringUuid(key, value) else this.remove(key) }

fun ListTag.asStringList() = this.map(Tag::getAsString)
fun ListTag.asBooleanList() = this.asByteList().map { it != 0.toByte() }
fun ListTag.asByteList() = this.mapNotNull { (it as? NumericTag)?.asByte }
fun ListTag.asShortList() = this.mapNotNull { (it as? NumericTag)?.asShort }
fun ListTag.asIntList() = this.mapNotNull { (it as? NumericTag)?.asInt }
fun ListTag.asLongList() = this.mapNotNull { (it as? NumericTag)?.asLong }
fun ListTag.asFloatList() = this.mapNotNull { (it as? NumericTag)?.asFloat }
fun ListTag.asDoubleList() = this.mapNotNull { (it as? NumericTag)?.asDouble }
fun ListTag.asListList() = this.mapNotNull { it as? ListTag }
fun ListTag.asCompoundList() = this.mapNotNull { it as? CompoundTag }
fun <T> ListTag.asCompoundObjectList(map: (nbt: CompoundTag) -> T?) = this.mapNotNull { (it as? CompoundTag)?.let(map) }
fun <T> ListTag.asStringObjectList(map: (nbt: String) -> T?) = this.mapNotNull { it.asString?.let(map) }
fun ListTag.asStringUuidList() = this.asStringObjectList(UUID::fromString)
fun ListTag.asComponentList() = this.asStringObjectList(Component.Serializer::fromJson)

fun ListTag.addString(value: String, position: Int = size) = this.add(position, StringTag.valueOf(value))
fun ListTag.addBoolean(value: Boolean, position: Int = size) = this.add(position, ByteTag.valueOf(value))
fun ListTag.addByte(value: Byte, position: Int = size) = this.add(position, ByteTag.valueOf(value))
fun ListTag.addShort(value: Short, position: Int = size) = this.add(position, ShortTag.valueOf(value))
fun ListTag.addInt(value: Int, position: Int = size) = this.add(position, IntTag.valueOf(value))
fun ListTag.addLong(value: Long, position: Int = size) = this.add(position, LongTag.valueOf(value))
fun ListTag.addFloat(value: Float, position: Int = size) = this.add(position, FloatTag.valueOf(value))
fun ListTag.addDouble(value: Double, position: Int = size) = this.add(position, DoubleTag.valueOf(value))
fun <T> ListTag.addCompoundObject(value: T, write: (obj: T) -> CompoundTag, position: Int = size) = this.add(position, write(value))
fun <T> ListTag.addStringObject(value: T, write: (obj: T) -> String, position: Int = size) = this.addString(write(value), position)
fun ListTag.addStringUuid(value: UUID, position: Int = size) = this.addStringObject(value, UUID::toString, position)
fun ListTag.addComponent(value: Component, position: Int = size) = this.addStringObject(value, Component.Serializer::toJson, position)

fun ListTag.addStrings(values: Iterable<String>) = this.addAll(values.map(StringTag::valueOf))
fun ListTag.addBooleans(values: Iterable<Boolean>) = this.addAll(values.map(ByteTag::valueOf))
fun ListTag.addBytes(values: Iterable<Byte>) = this.addAll(values.map(ByteTag::valueOf))
fun ListTag.addShorts(values: Iterable<Short>) = this.addAll(values.map(ShortTag::valueOf))
fun ListTag.addInts(values: Iterable<Int>) = this.addAll(values.map(IntTag::valueOf))
fun ListTag.addLongs(values: Iterable<Long>) = this.addAll(values.map(LongTag::valueOf))
fun ListTag.addFloats(values: Iterable<Float>) = this.addAll(values.map(FloatTag::valueOf))
fun ListTag.addDoubles(values: Iterable<Double>) = this.addAll(values.map(DoubleTag::valueOf))
fun <T> ListTag.addCompoundObjects(values: Iterable<T>, map: (obj: T) -> CompoundTag?) = this.addAll(values.mapNotNull(map))
fun <T> ListTag.addStringObjects(values: Iterable<T>, map: (obj: T) -> String?) = this.addStrings(values.mapNotNull(map))
fun ListTag.addStringUuid(values: Iterable<UUID>) = this.addStringObjects(values, UUID::toString)
fun ListTag.addComponent(values: Iterable<Component>) = this.addStringObjects(values, Component.Serializer::toJson)

fun CompoundTag.modifyString(key: String, ref: String = this.getString(key), modify: String.() -> String): String {
    val new = modify(ref)
    this.putString(key, new)
    return new
}
fun CompoundTag.modifyBoolean(key: String, ref: Boolean = this.getBoolean(key), modify: Boolean.() -> Boolean): Boolean {
    val new = modify(ref)
    this.putBoolean(key, new)
    return new
}
fun CompoundTag.modifyByte(key: String, ref: Byte = this.getByte(key), modify: Byte.() -> Byte): Byte {
    val new = modify(ref)
    this.putByte(key, new)
    return new
}
fun CompoundTag.modifyShort(key: String, ref: Short = this.getShort(key), modify: Short.() -> Short): Short {
    val new = modify(ref)
    this.putShort(key, new)
    return new
}
fun CompoundTag.modifyInt(key: String, ref: Int = this.getInt(key), modify: Int.() -> Int): Int {
    val new = modify(ref)
    this.putInt(key, new)
    return new
}
fun CompoundTag.modifyLong(key: String, ref: Long = this.getLong(key), modify: Long.() -> Long): Long {
    val new = modify(ref)
    this.putLong(key, new)
    return new
}
fun CompoundTag.modifyFloat(key: String, ref: Float = this.getFloat(key), modify: Float.() -> Float): Float {
    val new = modify(ref)
    this.putFloat(key, new)
    return new
}
fun CompoundTag.modifyDouble(key: String, ref: Double = this.getDouble(key), modify: Double.() -> Double): Double {
    val new = modify(ref)
    this.putDouble(key, new)
    return new
}
fun CompoundTag.modifyCompound(key: String, ref: CompoundTag = this.getCompound(key), modify: CompoundTag.() -> Unit): CompoundTag {
    modify(ref)
    this.put(key, ref)
    return ref
}
fun CompoundTag.modifyUuid(key: String, ref: UUID = this.getUUID(key), modify: UUID.() -> Unit): UUID {
    modify(ref)
    this.putUUID(key, ref)
    return ref
}
fun <T> CompoundTag.modifyCompoundObject(key: String, read: (nbt: CompoundTag) -> T, write: (obj: T) -> CompoundTag, modify: T.() -> Unit): CompoundTag {
    val new = getCompoundObject(key, read)
    modify(new)
    return putCompoundObject(key, new, write)
}
fun <T> CompoundTag.modifyStringObject(key: String, read: (string: String) -> T, write: (obj: T) -> String, modify: T.() -> Unit): String {
    val new = getStringObject(key, read)
    modify(new)
    return putStringObject(key, new, write)
}
fun CompoundTag.modifyStringUuid(key: String, modify: UUID.() -> Unit) = modifyStringObject(key, UUID::fromString, UUID::toString, modify)
fun CompoundTag.modifyComponent(key: String, modify: Component.() -> Unit) = modifyStringObject(key, { Component.Serializer.fromJson(it)!! }, Component.Serializer::toJson, modify)

fun CompoundTag.getStringList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt())) = ref.asStringList()
fun CompoundTag.getBooleanList(key: String, ref: ListTag = this.getList(key, Tag.TAG_BYTE.toInt())) = ref.asBooleanList()
fun CompoundTag.getByteList(key: String, ref: ListTag = this.getList(key, Tag.TAG_BYTE.toInt())) = ref.asByteList()
fun CompoundTag.getShortList(key: String, ref: ListTag = this.getList(key, Tag.TAG_SHORT.toInt())) = ref.asShortList()
fun CompoundTag.getIntList(key: String, ref: ListTag = this.getList(key, Tag.TAG_INT.toInt())) = ref.asIntList()
fun CompoundTag.getLongList(key: String, ref: ListTag = this.getList(key, Tag.TAG_LONG.toInt())) = ref.asLongList()
fun CompoundTag.getFloatList(key: String, ref: ListTag = this.getList(key, Tag.TAG_FLOAT.toInt())) = ref.asFloatList()
fun CompoundTag.getDoubleList(key: String, ref: ListTag = this.getList(key, Tag.TAG_DOUBLE.toInt())) = ref.asDoubleList()
fun CompoundTag.getListList(key: String, ref: ListTag = this.getList(key, Tag.TAG_LIST.toInt())) = ref.asListList()
fun CompoundTag.getCompoundList(key: String, ref: ListTag = this.getList(key, Tag.TAG_COMPOUND.toInt())) = ref.asCompoundList()
fun <T> CompoundTag.getCompoundObjectList(key: String, ref: ListTag = this.getList(key, Tag.TAG_COMPOUND.toInt()), map: (nbt: CompoundTag) -> T?) = ref.asCompoundObjectList(map)
fun <T> CompoundTag.getStringObjectList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt()), map: (string: String) -> T?) = ref.asStringObjectList(map)
fun CompoundTag.getStringUuidList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt())) = getStringObjectList(key, ref, UUID::fromString)
fun CompoundTag.getComponentList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt())) = getStringObjectList(key, ref, Component.Serializer::fromJson)

fun CompoundTag.putStringList(key: String, list: List<String>) = ListTag().apply { addAll(list.map(StringTag::valueOf)); this@putStringList.put(key, this) }
fun CompoundTag.putBooleanList(key: String, list: List<Boolean>) = ListTag().apply { addAll(list.map(ByteTag::valueOf)); this@putBooleanList.put(key, this) }
fun CompoundTag.putByteList(key: String, list: List<Byte>) = ListTag().apply { addAll(list.map(ByteTag::valueOf)); this@putByteList.put(key, this) }
fun CompoundTag.putShortList(key: String, list: List<Short>) = ListTag().apply { addAll(list.map(ShortTag::valueOf)); this@putShortList.put(key, this) }
fun CompoundTag.putIntList(key: String, list: List<Int>) = ListTag().apply { addAll(list.map(IntTag::valueOf)); this@putIntList.put(key, this) }
fun CompoundTag.putLongList(key: String, list: List<Long>) = ListTag().apply { addAll(list.map(LongTag::valueOf)); this@putLongList.put(key, this) }
fun CompoundTag.putFloatList(key: String, list: List<Float>) = ListTag().apply { addAll(list.map(FloatTag::valueOf)); this@putFloatList.put(key, this) }
fun CompoundTag.putDoubleList(key: String, list: List<Double>) = ListTag().apply { addAll(list.map(DoubleTag::valueOf)); this@putDoubleList.put(key, this) }
fun CompoundTag.putListList(key: String, list: List<ListTag>) = ListTag().apply { addAll(list); this@putListList.put(key, this) }
fun CompoundTag.putCompoundList(key: String, list: List<CompoundTag>) = ListTag().apply { addAll(list); this@putCompoundList.put(key, this) }
fun <T> CompoundTag.putCompoundObjectList(key: String, list: List<T>, write: (obj: T) -> CompoundTag?) = ListTag().apply { addAll(list.mapNotNull(write)); this@putCompoundObjectList.put(key, this) }
fun <T> CompoundTag.putStringObjectList(key: String, list: List<T>, write: (obj: T) -> String?) = putStringList(key, list.mapNotNull(write))
fun CompoundTag.putStringUuidList(key: String, list: List<UUID>) = putStringObjectList(key, list, UUID::toString)
fun CompoundTag.putComponentList(key: String, list: List<Component>) = putStringObjectList(key, list, Component.Serializer::toJson)

fun CompoundTag.modifyStringList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt()), modify: MutableList<String>.() -> List<String>?): ListTag {
    val objects = getStringList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putStringList(key, new)
}
fun CompoundTag.modifyBooleanList(key: String, ref: ListTag = this.getList(key, Tag.TAG_BYTE.toInt()), modify: MutableList<Boolean>.() -> List<Boolean>?): ListTag {
    val objects = getBooleanList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putBooleanList(key, new)
}
fun CompoundTag.modifyByteList(key: String, ref: ListTag = this.getList(key, Tag.TAG_BYTE.toInt()), modify: MutableList<Byte>.() -> List<Byte>?): ListTag {
    val objects = getByteList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putByteList(key, new)
}
fun CompoundTag.modifyShortList(key: String, ref: ListTag = this.getList(key, Tag.TAG_SHORT.toInt()), modify: MutableList<Short>.() -> List<Short>?): ListTag {
    val objects = getShortList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putShortList(key, new)
}
fun CompoundTag.modifyIntList(key: String, ref: ListTag = this.getList(key, Tag.TAG_INT.toInt()), modify: MutableList<Int>.() -> List<Int>?): ListTag {
    val objects = getIntList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putIntList(key, new)
}
fun CompoundTag.modifyLongList(key: String, ref: ListTag = this.getList(key, Tag.TAG_LONG.toInt()), modify: MutableList<Long>.() -> List<Long>?): ListTag {
    val objects = getLongList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putLongList(key, new)
}
fun CompoundTag.modifyFloatList(key: String, ref: ListTag = this.getList(key, Tag.TAG_FLOAT.toInt()), modify: MutableList<Float>.() -> List<Float>?): ListTag {
    val objects = getFloatList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putFloatList(key, new)
}
fun CompoundTag.modifyDoubleList(key: String, ref: ListTag = this.getList(key, Tag.TAG_DOUBLE.toInt()), modify: MutableList<Double>.() -> List<Double>?): ListTag {
    val objects = getDoubleList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putDoubleList(key, new)
}
fun CompoundTag.modifyListList(key: String, ref: ListTag = this.getList(key, Tag.TAG_LIST.toInt()), modify: MutableList<ListTag>.() -> List<ListTag>?): ListTag {
    val objects = getListList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putListList(key, new)
}
fun CompoundTag.modifyCompoundList(key: String, ref: ListTag = this.getList(key, Tag.TAG_COMPOUND.toInt()), modify: MutableList<CompoundTag>.() -> List<CompoundTag>?): ListTag {
    val objects = getCompoundList(key, ref).toMutableList()
    val new = modify(objects) ?: objects
    return putCompoundList(key, new)
}
fun <T> CompoundTag.modifyCompoundObjectList(key: String, read: (el: CompoundTag) -> T?, write: (el: T) -> CompoundTag?, ref: ListTag = this.getList(key, Tag.TAG_COMPOUND.toInt()), modify: MutableList<T>.() -> List<T>?): ListTag {
    val objects: MutableList<T> = getCompoundObjectList(key, ref, read).toMutableList()
    val new = modify(objects) ?: objects
    return putCompoundObjectList(key, new, write)
}
fun <T> CompoundTag.modifyStringObjectList(key: String, read: (el: String) -> T?, write: (el: T) -> String?, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt()), modify: MutableList<T>.() -> List<T>?): ListTag {
    val objects: MutableList<T> = getStringObjectList(key, ref, read).toMutableList()
    val new = modify(objects) ?: objects
    return putStringObjectList(key, new, write)
}
fun CompoundTag.modifyStringUuidList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt()), modify: MutableList<UUID>.() -> List<UUID>?) = modifyStringObjectList(key, UUID::fromString, UUID::toString, ref, modify)
fun CompoundTag.modifyComponentList(key: String, ref: ListTag = this.getList(key, Tag.TAG_STRING.toInt()), modify: MutableList<Component>.() -> List<Component>?) = modifyStringObjectList(key, Component.Serializer::fromJson, Component.Serializer::toJson, ref, modify)

fun CompoundTag.forEach(consumer: (key: String, value: Tag) -> Unit) = this.allKeys.forEach { consumer(it, this.get(it)!!) }
fun CompoundTag.forEachString(consumer: (key: String, value: String) -> Unit) = this.allKeys.forEach { consumer(it, this.getString(it)) }
fun CompoundTag.forEachBoolean(consumer: (key: String, value: Boolean) -> Unit) = this.allKeys.forEach { consumer(it, this.getBoolean(it)) }
fun CompoundTag.forEachByte(consumer: (key: String, value: Byte) -> Unit) = this.allKeys.forEach { consumer(it, this.getByte(it)) }
fun CompoundTag.forEachShort(consumer: (key: String, value: Short) -> Unit) = this.allKeys.forEach { consumer(it, this.getShort(it)) }
fun CompoundTag.forEachInt(consumer: (key: String, value: Int) -> Unit) = this.allKeys.forEach { consumer(it, this.getInt(it)) }
fun CompoundTag.forEachLong(consumer: (key: String, value: Long) -> Unit) = this.allKeys.forEach { consumer(it, this.getLong(it)) }
fun CompoundTag.forEachFloat(consumer: (key: String, value: Float) -> Unit) = this.allKeys.forEach { consumer(it, this.getFloat(it)) }
fun CompoundTag.forEachDouble(consumer: (key: String, value: Double) -> Unit) = this.allKeys.forEach { consumer(it, this.getDouble(it)) }
fun CompoundTag.forEachUuid(consumer: (key: String, value: UUID) -> Unit) = this.allKeys.forEach { consumer(it, this.getUUID(it)) }
fun CompoundTag.forEachList(type: Int, consumer: (key: String, value: ListTag) -> Unit) = this.allKeys.forEach { consumer(it, this.getList(it, type)) }
fun CompoundTag.forEachCompound(consumer: (key: String, value: CompoundTag) -> Unit) = this.allKeys.forEach { consumer(it, this.getCompound(it)) }
fun <T> CompoundTag.forEachCompoundObject(read: (el: CompoundTag) -> T?, consumer: (key: String, value: T) -> Unit) = this.allKeys.forEach { consumer(it, read(this.getCompound(it)) ?: return@forEach) }
fun <T> CompoundTag.forEachStringObject(read: (el: String) -> T?, consumer: (key: String, value: T) -> Unit) = this.allKeys.forEach { consumer(it, read(this.getString(it)) ?: return@forEach) }
fun CompoundTag.forEachStringUuid(consumer: (key: String, value: UUID) -> Unit) = forEachStringObject(UUID::fromString, consumer)
fun CompoundTag.forEachComponent(consumer: (key: String, value: Component) -> Unit) = forEachStringObject(Component.Serializer::fromJson, consumer)

fun CompoundTag.forEachStringList(consumer: (key: String, value: List<String>) -> Unit) = this.allKeys.forEach { consumer(it, this.getStringList(it)) }
fun CompoundTag.forEachBooleanList(consumer: (key: String, value: List<Boolean>) -> Unit) = this.allKeys.forEach { consumer(it, this.getBooleanList(it)) }
fun CompoundTag.forEachByteList(consumer: (key: String, value: List<Byte>) -> Unit) = this.allKeys.forEach { consumer(it, this.getByteList(it)) }
fun CompoundTag.forEachShortList(consumer: (key: String, value: List<Short>) -> Unit) = this.allKeys.forEach { consumer(it, this.getShortList(it)) }
fun CompoundTag.forEachIntList(consumer: (key: String, value: List<Int>) -> Unit) = this.allKeys.forEach { consumer(it, this.getIntList(it)) }
fun CompoundTag.forEachLongList(consumer: (key: String, value: List<Long>) -> Unit) = this.allKeys.forEach { consumer(it, this.getLongList(it)) }
fun CompoundTag.forEachFloatList(consumer: (key: String, value: List<Float>) -> Unit) = this.allKeys.forEach { consumer(it, this.getFloatList(it)) }
fun CompoundTag.forEachDoubleList(consumer: (key: String, value: List<Double>) -> Unit) = this.allKeys.forEach { consumer(it, this.getDoubleList(it)) }
fun CompoundTag.forEachListList(consumer: (key: String, value: List<ListTag>) -> Unit) = this.allKeys.forEach { consumer(it, this.getListList(it)) }
fun CompoundTag.forEachCompoundList(consumer: (key: String, value: List<CompoundTag>) -> Unit) = this.allKeys.forEach { consumer(it, this.getCompoundList(it)) }
fun <T> CompoundTag.forEachCompoundObjectList(read: (el: CompoundTag) -> T?, consumer: (key: String, value: List<T>) -> Unit) = this.allKeys.forEach { consumer(it, this.getCompoundObjectList(it, map = read)) }
fun <T> CompoundTag.forEachStringObjectList(read: (el: String) -> T?, consumer: (key: String, value: List<T>) -> Unit) = this.allKeys.forEach { consumer(it, this.getStringObjectList(it, map = read)) }
fun CompoundTag.forEachStringUuidList(consumer: (key: String, value: List<UUID>) -> Unit) = forEachStringObjectList(UUID::fromString, consumer)
fun CompoundTag.forEachComponentList(consumer: (key: String, value: List<Component>) -> Unit) = forEachStringObjectList(Component.Serializer::fromJson, consumer)