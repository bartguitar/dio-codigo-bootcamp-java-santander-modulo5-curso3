package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.list;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.OwnerId;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Proposal;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllStrategy implements Strategy {

    private final ProposalRepository proposalRepository;

    public AllStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return  proposalRepository.findAll();
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.ALL;
    }
}
