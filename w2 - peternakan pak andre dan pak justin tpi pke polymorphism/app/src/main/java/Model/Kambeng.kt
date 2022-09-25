package Model

class Kambeng(nama: String, usia: Int, imageternak: String) : Ternak(nama, usia, imageternak) {
    override fun interaction(): String {
        var cetung = "Embekkkkkk Embekkkkkk MbeKKK!!"

        return cetung;
    }
}