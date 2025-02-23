package uz.gita.otabek.memorygame.utils

enum class GeneralEnums(val value: String) {
    APP("memory"),
}

enum class LevelEnums(val horCount: Int, val verCount: Int) {
    EASY(4, 3),
    MEDIUM(6, 4),
    HARD(8, 6)
}

enum class LanguageEnums(val value: String) {
    EN("en"), UZ("uz"), RU("ru")
}