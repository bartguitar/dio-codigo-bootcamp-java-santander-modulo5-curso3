package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.input;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Owner;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Proposal;

import java.util.Optional;

public record CreateProposalInput(String title, Optional<String> description) {
    public Proposal toDomain(Owner owner) {
        return new Proposal(title, description, owner);
    }
}
