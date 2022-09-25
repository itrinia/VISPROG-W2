package Model


import android.os.Parcel
import android.os.Parcelable

open class Ternak(
    var nama:String?,
    var usia:Int?,
    var jenis:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    var imageternak: String = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeValue(usia)
        parcel.writeString(jenis)
    }

    open fun mkn(ayam: String): String {
        return "Kamu memberi makan ayam kesayanganmu dg biji-bijian";
    }

    open fun mkn(sapiKambing: Int): String {
        return "Kamu memberi makan ternakmu dg rumput";
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ternak> {
        override fun createFromParcel(parcel: Parcel): Ternak {
            return Ternak(parcel)
        }

        override fun newArray(size: Int): Array<Ternak?> {
            return arrayOfNulls(size)
        }
    }

    open fun interaction(): String {
        TODO("Not yet implemented")
    }
}