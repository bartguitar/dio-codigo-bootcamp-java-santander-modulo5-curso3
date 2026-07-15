package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.infrastructure.http.request;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.input.CreateProposalInput;

import java.util.Optional;

public record CreateProposalRequest(String title, Optional<String> description) {
    public CreateProposalInput toInput() {
        return new CreateProposalInput(title, description);
    }
}