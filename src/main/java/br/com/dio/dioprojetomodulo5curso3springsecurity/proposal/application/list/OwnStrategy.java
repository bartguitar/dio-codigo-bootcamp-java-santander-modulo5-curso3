package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.list;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.OwnerId;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Proposal;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnStrategy implements Strategy {

    private final ProposalRepository proposalRepository;


    public OwnStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }


    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.OWN;
    }
}
