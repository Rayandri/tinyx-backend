package com.epita.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/search/post")
@ApplicationScoped
public class PostSearchService {
    /*
    * here are the endpoints for all post search
    * */

    @GET
    @Path("/{regex}")
    public Response searchPost(@PathParam("regex") String regex) {
        /*
        * search all the post following the regex
        * reminder of the rules :
        *  if the regex is "word" -> we are looking only for post contaignin the word "word"
        *       THIS DO NOT INCLUDE #, #word doesn't count
        *  if the regex is "#word" -> we are loking only for post containing the # "#word"
        *       THIS do no include "word", any message with "word" without the # is not good
        *
        *   if the regex is "word#word" -> we look for all message with "word" AND "#word"
        * */

        return Response.ok().build();
    }
}
