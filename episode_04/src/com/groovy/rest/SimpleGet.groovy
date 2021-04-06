package com.ea.groovy.rest

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')
@Grab('oauth.signpost:signpost-core:1.2.1.2')
@Grab('oauth.signpost:signpost-commonshttp4:1.2.1.2')

String base = 'https://recommendations-lt.tnt-ea.com'

def restClient = new RESTClient(base)
restClient.setHeaders([Authorization: "Bearer QVQwOjEuMDozLjA6NjA6MFZUSWlWcjVoMkRIVFNqZEVyQ0gza3BmTjh3VW1mbHhFZzozMjcwNDpubWJmcg="])
restClient.contentType = ContentType.JSON
restClient.get( path : '/v1/recommendations/2365054010/friends' ) {
    response, json ->
        println response.status
        println json

}