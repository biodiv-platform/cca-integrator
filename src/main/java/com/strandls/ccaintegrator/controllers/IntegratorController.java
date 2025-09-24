package com.strandls.ccaintegrator.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.strandls.ccaintegrator.ApiConstants;
import com.strandls.ccaintegrator.pojo.UserProfileData;
import com.strandls.ccaintegrator.services.IntegratorServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author vilay
 *
 */
@Tag(name = "Integrator Services")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class IntegratorController {

    @Inject
    private IntegratorServices services;

    @GET
    @Path(ApiConstants.PING)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Ping pong", description = "Returns PONG for health check")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Unable to get pong",
            content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(implementation = String.class)))
    })
    public Response getPing() {
        return Response.status(Status.OK).entity("PONG").build();
    }

    @GET
    @Path(ApiConstants.READ + ApiConstants.PROFILE + "/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Find by user id", description = "Returns the user profile data")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User profile found",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserProfileData.class))),
        @ApiResponse(responseCode = "400", description = "Unable to fetch the data",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
    })
    public Response findUserProfileById(
            @Context HttpServletRequest request,
            @Parameter(description = "User identifier") @PathParam("id") String userId) {
        try {
            UserProfileData result = services.fetchUserProfileById(request, userId);
            return Response.status(Status.OK).entity(result).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
