package com.example.prak9.repo

import com.example.prak9.room.Siswa
import com.example.prak9.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepoSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)

    fun getSiswaStream(id: Int): Flow<Siswa?>
}

class OfflineRepoSiswa(
    private val siswaDao: SiswaDao
) : RepoSiswa{
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id)
}

