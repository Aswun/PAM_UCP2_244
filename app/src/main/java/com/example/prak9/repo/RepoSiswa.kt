package com.example.prak9.repo

import com.example.prak9.room.Siswa
import com.example.prak9.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepoSiswa {
    fun GetAllSiswaStream(): Flow<List<Siswa>>
    suspend fun InsertSiswa(siswa: Siswa)
}

class OfflineRepoSiswa(
    private val siswaDao: SiswaDao
) : RepoSiswa{
    override fun GetAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun InsertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
}

