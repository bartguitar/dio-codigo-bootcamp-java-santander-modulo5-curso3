package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.list.AccessScope;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.list.Factory;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.output.ProposalOutput;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.OwnerId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProposalsUseCase {
    private final Factory factory;

    public ListProposalsUseCase(Factory factory) {
        this.factory = factory;
    }

    public List<ProposalOutput> execute(AccessScope scope, OwnerId ownerId) {
        var proposals = factory.getStrategy(scope).getProposals(ownerId);

        return proposals.stream().map(ProposalOutput::from).toList();
    }
}