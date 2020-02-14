package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors


/**
 * Thrown when there was an error converting the entity to the dto or vice versa
 *
 * @property message user ready error message
 * @property cause the original cause of this exception
 */
class ConvertingError (message: String, cause: Throwable) : Throwable(message, cause)
