package Model

class Sapi(nama: String, usia: Int, imageternak: String) : Ternak(nama, usia, imageternak) {
    override fun interaction(): String {
        var cetung = "Moooo Moooooo Emoooooo!!"

        return cetung;
    }
}