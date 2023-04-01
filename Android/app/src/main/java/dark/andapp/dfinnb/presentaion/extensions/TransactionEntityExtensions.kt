package dark.andapp.dfinnb.presentaion.extensions

import java.text.SimpleDateFormat
import java.util.*

typealias DomainTE = dark.andapp.dfinnb.domain.entity.TransactionEntity;
typealias DataTE = dark.andapp.dfinnb.data.local.entity.TransactionEntity;


fun DataTE.toDomain(): DomainTE {
    return DomainTE(
        id = this.id,
        name = this.name,
        amount = this.amount,
        comment = this.comment,
        createdAt = this.createdAt,
    )
}