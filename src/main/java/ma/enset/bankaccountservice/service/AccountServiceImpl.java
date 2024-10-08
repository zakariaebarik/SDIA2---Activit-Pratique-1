package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<BankAccountResponseDTO> getAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream()
                .map((bankAccount)->accountMapper.fromBankAccount(bankAccount))
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO requestDTO) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(requestDTO.getBalance() != null) account.setBalance(requestDTO.getBalance());
        if(requestDTO.getType() != null) account.setType(requestDTO.getType());
        if(requestDTO.getCurrency() != null) account.setCurrency(requestDTO.getCurrency());
        return accountMapper.fromBankAccount(account);
    }

    @Override
    public void removeAccount(String id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return accountMapper.fromBankAccount(savedBankAccount);
    }


}
