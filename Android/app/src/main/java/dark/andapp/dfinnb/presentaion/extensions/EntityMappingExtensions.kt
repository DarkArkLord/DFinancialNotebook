package dark.andapp.dfinnb.presentaion.extensions

import dark.andapp.dfinnb.domain.entity.NamedEntity

typealias DataBA = dark.andapp.dfinnb.data.local.entity.BankAccountEntity;
typealias DataC = dark.andapp.dfinnb.data.local.entity.CategoryEntity;
typealias DataT = dark.andapp.dfinnb.data.local.entity.TransactionEntity;

typealias DomainT = dark.andapp.dfinnb.domain.entity.TransactionEntity;

fun DataBA.toDomain(): NamedEntity {
    return NamedEntity(
        id = this.id,
        name = this.name,
    )
}

fun DataC.toDomain(): NamedEntity {
    return NamedEntity(
        id = this.id,
        name = this.name,
    )
}

fun DataT.toDomain(bankAccount: NamedEntity, category: NamedEntity): DomainT {
    return DomainT(
        id = this.id,
        bank = bankAccount,
        category = category,
        amount = this.amount,
        createdAt = this.createdAt,
        comment = this.comment,
    )
}

fun DomainT.toData(): DataT {
    return DataT(
        id = this.id,
        bankId = this.bank.id,
        categoryId = this.category.id,
        amount = this.amount,
        createdAt = this.createdAt,
        comment = this.comment,
    )
}