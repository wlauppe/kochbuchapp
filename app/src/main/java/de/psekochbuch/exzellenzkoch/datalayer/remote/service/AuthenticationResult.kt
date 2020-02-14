package de.psekochbuch.exzellenzkoch.datalayer.remote.service

enum class AuthenticationResult {
    REGISTRATIONSUCCESS,
    REGISTRATIONFAILED,
    CANNOTCONTECTTOSERVER,
    CANNOTCONTECTTOFIREBASE,
    USERALREADYEXIST
}