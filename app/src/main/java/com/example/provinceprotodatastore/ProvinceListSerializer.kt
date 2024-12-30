package com.example.provinceprotodatastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProvinceListSerializer : Serializer<ProvinceList> {
    override val defaultValue: ProvinceList = ProvinceList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProvinceList {
        try {
            return ProvinceList.parseFrom(input)
        }catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)

        }
    }

    override suspend fun writeTo(t: ProvinceList, output: OutputStream) {
        t.writeTo(output)

    }
}