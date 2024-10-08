package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import ma.enset.bankaccountservice.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AccountGraphQLController {
    private AccountService accountService;
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    public AccountGraphQLController(AccountService accountService, BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountsById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found!", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addAccount(bankAccountRequestDTO);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.updateAccount(id, bankAccountRequestDTO);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
        accountService.removeAccount(id);

    }


}
