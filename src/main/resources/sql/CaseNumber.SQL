select cases.CaseNumber
from TOCCase cases
left join TOCCaseAlias tca on cases.C = tca.C_OCCASE_CaseAlias and  cases.I  = tca.I_OCCASE_CaseAlias
where tca.Value = '&caseAliasValue&'
