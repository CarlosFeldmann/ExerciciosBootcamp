package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RouteNotFoundError extends ApiError {
  private String route;

  public RouteNotFoundError(String route) {
    super("route_not_found", "Unable to find route!", HttpStatus.NOT_FOUND.value());
    this.route = route;
  }
}
