package dark.andapp.dfinnb.domain.entity

data class NamedEntity(
    var id: Int,
    var name: String,
    var count: Int = 0,
    var amount: Double = 0.0,
)