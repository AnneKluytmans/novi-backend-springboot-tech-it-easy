package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;

public class AuthorityRequestDTO {
    @NotBlank(message = "Authority must not be blank")
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
