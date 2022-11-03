package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface TransferDao {

    void createTransfer(Transfer transfer);

    Transfer insertTransfer(Transfer transfer);

    List<Transfer> getTransfersByUserId(int userId);

    //public Transfer updateBalances();

    //Transfer resultToTransfer (int transferId);

    //List<Integer> getAllTransferIds();

    Transfer getTransferByTransferId(int id);

    Transfer getStatusByStatusId(int statusId);

    String getTypeByTypeId(int typeId);

    int getTransfer(int transfer);
}

