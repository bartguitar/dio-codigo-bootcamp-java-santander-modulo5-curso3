package br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.application.list;

import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.OwnerId;
import br.com.dio.dioprojetomodulo5curso3springsecurity.proposal.domain.Proposal;

import java.util.List;

public interface Strategy {
    List<Proposal> getProposals(OwnerId ownerId);
    AccessScope getScope();
}
