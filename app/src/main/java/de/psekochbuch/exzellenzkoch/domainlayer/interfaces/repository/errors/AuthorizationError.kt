package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors

/**
 * Thrown when there was a error with authorization
 *
 * @property message user ready error message
 * @property cause the original cause of this exception
 */
class AuthorizationError (message: String, cause: Throwable) : Throwable(message, cause)
