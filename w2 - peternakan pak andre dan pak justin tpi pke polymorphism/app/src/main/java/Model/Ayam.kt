package Model

class Ayam(nama: String, usia: Int, imageternak: String) : Ternak(nama, usia, imageternak) {
    override fun interaction(): String {
        var cetung = "Petok Petook Petokkkkk!!"

        return cetung;
    }
}