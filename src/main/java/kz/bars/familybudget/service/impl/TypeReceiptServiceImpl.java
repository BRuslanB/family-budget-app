package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.TypeReceiptDto;
import kz.bars.familybudget.mapper.TypeReceiptMapper;
import kz.bars.familybudget.model.TypeReceipt;
import kz.bars.familybudget.repository.TypeReceiptRepo;
import kz.bars.familybudget.service.TypeReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeReceiptServiceImpl implements TypeReceiptService {

    private final TypeReceiptRepo typeReceiptRepo;
    private final TypeReceiptMapper typeReceiptMapper;

    @Override
    public List<TypeReceipt> getAllTypeReceipt() {
        return typeReceiptRepo.findAll();
    }

    @Override
    public TypeReceipt getTypeReceipt(Long id) {
        return typeReceiptRepo.findById(id).orElseThrow();
    }

    @Override
    public TypeReceipt addTypeReceipt(TypeReceipt typeReceipt) {
        return typeReceiptRepo.save(typeReceipt);
    }

    @Override
    public TypeReceipt updateTypeReceipt(TypeReceipt typeReceipt) {
        return typeReceiptRepo.save(typeReceipt);
    }

    @Override
    public void deleteTypeReceipt(Long id) {
        typeReceiptRepo.deleteById(id);
    }

    @Override
    public List<TypeReceiptDto> getAllTypeReceiptDto() {
        return typeReceiptMapper.toDtoList(typeReceiptRepo.findAll());
    }

    @Override
    public TypeReceiptDto getTypeReceiptDto(Long id) {
        return typeReceiptMapper.toDto(typeReceiptRepo.findById(id).orElseThrow());
    }

    @Override
    public TypeReceiptDto addTypeReceiptDto(TypeReceiptDto typeReceiptDto) {
        return typeReceiptMapper.toDto(typeReceiptRepo.save(typeReceiptMapper.toEntity(typeReceiptDto)));
    }

    @Override
    public TypeReceiptDto updateTypeReceiptDto(TypeReceiptDto typeReceiptDto) {
        return typeReceiptMapper.toDto(typeReceiptRepo.save(typeReceiptMapper.toEntity(typeReceiptDto)));
    }
}
