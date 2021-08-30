package com.mayson.listadecontatossimples

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contact(
    var nome: String,
    var telefone: String,
    var imagem: String

) : Parcelable