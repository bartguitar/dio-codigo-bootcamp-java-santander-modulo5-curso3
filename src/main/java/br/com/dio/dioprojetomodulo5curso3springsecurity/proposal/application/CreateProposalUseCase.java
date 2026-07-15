package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.input.CreateProposalInput;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.output.ProposalOutput;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Owner;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProposalUseCase {
    private final ProposalRepository proposalRepository;

    public CreateProposalUseCase(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public ProposalOutput execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        var saved = proposalRepository.save(proposal);

        return ProposalOutput.from(saved);
    }
}