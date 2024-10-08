package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import ma.enset.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private AccountService accountService;




    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;

    }

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> bankAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@PathVariable String id, @RequestBody BankAccountRequestDTO requestDTO){

        return accountService.updateAccount(id, requestDTO);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        accountService.removeAccount(id);
    }
}
