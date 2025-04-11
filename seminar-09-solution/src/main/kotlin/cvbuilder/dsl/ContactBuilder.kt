package cvbuilder.dsl

import cvbuilder.models.Contact

@CVDsl
class ContactBuilder {
    var name: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    fun build(): Contact = Contact(name, email, phoneNumber)
}
