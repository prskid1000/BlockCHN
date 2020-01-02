//
// Created by Dave Nash on 20/10/2017.
//

#include "Blockchain.h"

Blockchain::Blockchain()
{
    _vChain.emplace_back(Block(0, "Genesis Block"));
    _nDifficulty = 6;
}

string Blockchain::AddBlock(Block bNew)
{
    bNew.sPrevHash = _GetLastBlock().sHash;
    string tmp=bNew.MineBlock(_nDifficulty);
    _vChain.push_back(bNew);
    return tmp;
}

Block Blockchain::_GetLastBlock() const
{
    return _vChain.back();
}
