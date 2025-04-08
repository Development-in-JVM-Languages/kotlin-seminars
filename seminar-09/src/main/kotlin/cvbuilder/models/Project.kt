package cvbuilder.models

data class Project(
    val name: String,
    val description: String,
    val technologies: List<String>,
    val url: String = "",
    val year: String = ""
)
