package dark.andapp.dfinnb.presentaion.extensions

typealias DataBA = dark.andapp.dfinnb.data.local.entity.BankAccountEntity;
typealias DataC = dark.andapp.dfinnb.data.local.entity.CategoryEntity;
typealias DataT = dark.andapp.dfinnb.data.local.entity.TransactionEntity;

typealias DomainBA = dark.andapp.dfinnb.domain.entity.BankAccountEntity;
typealias DomainC = dark.andapp.dfinnb.domain.entity.CategoryEntity;
typealias DomainT = dark.andapp.dfinnb.domain.entity.TransactionEntity;

fun DataBA.toDomain(): DomainBA {
    return DomainBA(
        id = this.id,
        name = this.name,
    )
}

fun DataC.toDomain(): DomainC {
    return DomainC(
        id = this.id,
        name = this.name,
    )
}

fun DataT.toDomain(bankAccount: DomainBA, category: DomainC): DomainT {
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