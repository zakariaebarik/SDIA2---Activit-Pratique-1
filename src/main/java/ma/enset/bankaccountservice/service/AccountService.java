package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;

import java.util.List;

public interface AccountService {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    List<BankAccountResponseDTO> getAccounts();

    BankAccountResponseDTO getAccountById(String id);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO requestDTO);

    void removeAccount(String id);
}
