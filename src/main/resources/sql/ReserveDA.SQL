with CTE_PolicyLookUp as (
    select
        MaxStr_1 as PolicyVersion,
        MaxStr_2 as DAType,
        MinNum_4 as MinVal,
        MaxNum_4 as MaxVal,
        MaxNum_3 as Level,
        AnswerStr as Role
    from TRow tr
),
CTE_ClaimSummary AS (
    select
        theCase.SubCaseNumber,
        ISNULL(contractStub.ReferenceNumb, '') + '-' + ISNULL(contractStub.ReferenceNum2, '') + '-' + ISNULL(contractStub.ReferenceNum3, '') + '-' + ISNULL(contractStub.ReferenceNum4, '') + '-' + ISNULL(contractStub.ReferenceNum5, '') as PolicyVersion,
        tol_r.ReserveAmount_MonAmt,
        to_di2.Name as ReserveStatus,
        contractStub.ReferenceNum3 as Product,
        sum(case when left(theCase.SubCaseNumber, 8) = left('&sPaymentbenefit&', 8) and (to_di2.Name = 'Awaiting Approval'OR to_di2.Name = 'Approved') then tol_r.ReserveAmount_MonAmt else 0 end)
            over (partition by left(theCase.SubCaseNumber, 8) order by left(theCase.SubCaseNumber, 8)) as Amount,
        row_number() OVER (partition by theCase.SubCaseNumber ORDER by theCase.SubCaseNumber) as rnk
    from (select * from VGBESBClaimDetails where topcase = left('&sPaymentbenefit&', 8)) theCase
    inner join TOCCase toc_c on (toc_c.I = theCase.SubCaseI)
    left join TOCCase tClaimcase on theCase.SubCaseC = tClaimcase.C and theCase.SubCaseI = tClaimcase.I
    inner join ROCCASEOCCTRSTBContractsOnCa as casePolicyVersion on (casePolicyVersion.I_from = theCase.TopCaseI) and (casePolicyVersion.C_from = theCase.TopCaseC)
    inner join TOCContractStub contractStub on (casePolicyVersion.I_To = contractStub.I) and (casePolicyVersion.C_To = contractStub.C)
    left join TOLBenefit tol_b on tol_b.C_OCCASE_BenefitCase = toc_c.C and tol_b.I_OCCASE_BenefitCase = toc_c.I
    left join TOLReserve tol_r on tol_b.C = tol_r.C_BNFT_Reserves and tol_b.I = tol_r.I_BNFT_Reserves
    left join TODomainInstance to_di2 on to_di2.I = tol_r.ReserveStatus
),
CTE_ReserveDA as (
    select
        Distinct SubCaseNumber,
        cte_s.PolicyVersion,
        Amount,
        COALESCE(COALESCE(cte_pv.Role, cte_dpv.Role), cte_ddd.Role) as Role,
        COALESCE(COALESCE(cte_pv.MinVal, cte_dpv.MinVal), cte_ddd.MinVal) as MinVal,
        COALESCE(COALESCE(cte_pv.MaxVal, cte_dpv.MaxVal), cte_ddd.MaxVal) as MaxVal,
        COALESCE(COALESCE(cte_pv.DAType, cte_dpv.DAType), cte_ddd.DAType) as DAType,
        COALESCE(COALESCE(cte_pv.Level, cte_dpv.Level), cte_ddd.Level) as Level
    from CTE_ClaimSummary cte_s
    left join CTE_PolicyLookUp cte_pv on cte_s.PolicyVersion = cte_pv.PolicyVersion and cte_pv.DAType = '&sDAType&' and Amount  >= cte_pv.MinVal and Amount  <= cte_pv.MaxVal
    left join CTE_PolicyLookUp cte_dpv on Amount  >= cte_dpv.MinVal and Amount  <= cte_dpv.MaxVal and cte_dpv.DAType = '&sDAType&'
        and NOT EXISTS (select 1 from CTE_PolicyLookUp cp WHERE cp.PolicyVersion = cte_s.PolicyVersion and cp.DAType = '&sDAType&')
    left join CTE_PolicyLookUp cte_ddd on Amount  >= cte_ddd.MinVal and Amount  <= cte_ddd.MaxVal and cte_ddd.DAType ='&sDAType&'
        and NOT EXISTS (select 1 from CTE_PolicyLookUp cp WHERE cp.DAType = '&sDAType&') and rnk = 1
)

select * from CTE_ReserveDA
WHERE 1 = 1
and SubCaseNumber = '&sPaymentbenefit&'
order by Level
