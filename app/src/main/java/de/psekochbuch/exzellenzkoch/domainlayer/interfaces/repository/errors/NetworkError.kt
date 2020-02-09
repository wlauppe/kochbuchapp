package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors


/**
 * Thrown when there was a general network error
 *
 * @property message user ready error message
 * @property cause the original cause of this exception
 */
class NetworkError (message: String, cause: Throwable) : Throwable(message, cause)
