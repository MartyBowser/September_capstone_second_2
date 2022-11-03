package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    void createTransfer(Transfer transfer);

    Transfer insertTransfer(Transfer transfer);

    Transfer getTransferByUserId(int transferId);

    //public Transfer updateBalances();

}