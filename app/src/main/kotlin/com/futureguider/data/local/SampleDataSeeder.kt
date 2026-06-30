package com.futureguider.data.local

import com.futureguider.data.local.dao.CareerDetailDao
import com.futureguider.data.local.dao.CareerNodeDao
import com.futureguider.data.local.entities.CareerDetailEntity
import com.futureguider.data.local.entities.CareerNodeEntity
import com.futureguider.data.local.entities.CertificationEntity
import com.futureguider.data.local.entities.ProjectEntity
import com.futureguider.data.local.entities.SkillEntity

object SampleDataSeeder {

    suspend fun seed(db: FutureGuiderDatabase) {
        val nodeDao = db.careerNodeDao()
        val detailDao = db.careerDetailDao()
        if (nodeDao.getCount() > 0) return

        // ROOT NODES
        val roots = listOf(
            CareerNodeEntity(id = 1,  name = "10th Passed",   parentId = null, type = "ROOT", colorHex = "#6366F1"),
            CareerNodeEntity(id = 2,  name = "12th Science",  parentId = null, type = "ROOT", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 3,  name = "12th Commerce", parentId = null, type = "ROOT", colorHex = "#EC4899"),
            CareerNodeEntity(id = 4,  name = "12th Arts",     parentId = null, type = "ROOT", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 5,  name = "Diploma",       parentId = null, type = "ROOT", colorHex = "#10B981"),
            CareerNodeEntity(id = 6,  name = "BCA",           parentId = null, type = "ROOT", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 7,  name = "B.Sc",          parentId = null, type = "ROOT", colorHex = "#14B8A6"),
            CareerNodeEntity(id = 8,  name = "B.Com",         parentId = null, type = "ROOT", colorHex = "#F97316"),
            CareerNodeEntity(id = 9,  name = "B.Tech",        parentId = null, type = "ROOT", colorHex = "#6366F1"),
            CareerNodeEntity(id = 10, name = "MBA",           parentId = null, type = "ROOT", colorHex = "#DC2626"),
            CareerNodeEntity(id = 11, name = "B.Ed",          parentId = null, type = "ROOT", colorHex = "#7C3AED")
        )
        nodeDao.insertAll(roots)

        // 10th Passed children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 12, name = "Science Stream",  parentId = 1, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 13, name = "Commerce Stream", parentId = 1, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 14, name = "Arts Stream",     parentId = 1, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 15, name = "Diploma (ITI)",   parentId = 1, type = "BRANCH", colorHex = "#6366F1")
        ))

        // Science Stream children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 16, name = "PCMB",         parentId = 12, type = "BRANCH", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 17, name = "PCMC",         parentId = 12, type = "BRANCH", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 18, name = "PCME",         parentId = 12, type = "BRANCH", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 19, name = "Pure Science", parentId = 12, type = "BRANCH", colorHex = "#8B5CF6")
        ))

        // PCMB leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 20, name = "MBBS / Medicine",    parentId = 16, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 21, name = "BDS (Dentistry)",    parentId = 16, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 22, name = "B.Pharm",            parentId = 16, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 23, name = "Biotechnology B.Sc", parentId = 16, type = "LEAF", colorHex = "#10B981")
        ))

        // PCMC leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 24, name = "B.Tech CSE",           parentId = 17, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 25, name = "AI & Machine Learning", parentId = 17, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 26, name = "Cyber Security",        parentId = 17, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 27, name = "Data Science",          parentId = 17, type = "LEAF", colorHex = "#3B82F6")
        ))

        // PCME leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 28, name = "Mechanical Engg",      parentId = 18, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 29, name = "Civil Engineering",     parentId = 18, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 30, name = "Electrical Engg",       parentId = 18, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 31, name = "Aerospace Engineering", parentId = 18, type = "LEAF", colorHex = "#F59E0B")
        ))

        // Pure Science leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 32, name = "B.Sc Physics",     parentId = 19, type = "LEAF", colorHex = "#14B8A6"),
            CareerNodeEntity(id = 33, name = "B.Sc Chemistry",   parentId = 19, type = "LEAF", colorHex = "#14B8A6"),
            CareerNodeEntity(id = 34, name = "B.Sc Mathematics", parentId = 19, type = "LEAF", colorHex = "#14B8A6")
        ))

        // Commerce Stream children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 35, name = "Accounting & Finance", parentId = 13, type = "BRANCH", colorHex = "#EC4899"),
            CareerNodeEntity(id = 36, name = "Business Studies",     parentId = 13, type = "BRANCH", colorHex = "#EC4899"),
            CareerNodeEntity(id = 37, name = "Economics",            parentId = 13, type = "BRANCH", colorHex = "#EC4899")
        ))

        // Commerce leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 38, name = "CA (Chartered Accountant)", parentId = 35, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 39, name = "CMA",                       parentId = 35, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 40, name = "B.Com (Hons)",              parentId = 35, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 41, name = "BBA",                       parentId = 36, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 42, name = "MBA Finance",               parentId = 36, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 43, name = "B.Sc Economics",            parentId = 37, type = "LEAF", colorHex = "#F97316"),
            CareerNodeEntity(id = 44, name = "Investment Banking",        parentId = 37, type = "LEAF", colorHex = "#F97316")
        ))

        // Arts Stream children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 47, name = "Humanities",     parentId = 14, type = "BRANCH", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 48, name = "Design & Media", parentId = 14, type = "BRANCH", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 49, name = "Law",            parentId = 14, type = "BRANCH", colorHex = "#F59E0B")
        ))

        // Arts leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 50, name = "BA Political Science", parentId = 47, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 51, name = "BA Psychology",        parentId = 47, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 52, name = "BA Sociology",         parentId = 47, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 53, name = "B.Des (UI/UX Design)", parentId = 48, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 54, name = "Mass Communication",   parentId = 48, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 55, name = "Animation & VFX",      parentId = 48, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 56, name = "LLB",                  parentId = 49, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 57, name = "BA LLB (5 Year)",      parentId = 49, type = "LEAF", colorHex = "#EF4444")
        ))

        // Diploma ITI leaves
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 60, name = "Electrician Trade",  parentId = 15, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 61, name = "Fitter Trade",       parentId = 15, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 62, name = "Welder Trade",       parentId = 15, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 63, name = "Computer Operator",  parentId = 15, type = "LEAF", colorHex = "#10B981")
        ))

        // 12th Science children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 64, name = "Engineering",  parentId = 2, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 65, name = "Medical",      parentId = 2, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 66, name = "Pure Science", parentId = 2, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 67, name = "Technology",   parentId = 2, type = "BRANCH", colorHex = "#6366F1")
        ))

        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 68, name = "B.Tech CSE",        parentId = 64, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 69, name = "B.Tech ECE",        parentId = 64, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 70, name = "B.Tech Mechanical", parentId = 64, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 71, name = "B.Tech Civil",      parentId = 64, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 72, name = "MBBS",              parentId = 65, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 73, name = "BDS",               parentId = 65, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 74, name = "BAMS (Ayurveda)",   parentId = 65, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 75, name = "B.Pharm",           parentId = 65, type = "LEAF", colorHex = "#10B981"),
            CareerNodeEntity(id = 76, name = "B.Tech AI & ML",       parentId = 67, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 77, name = "B.Tech Data Science",   parentId = 67, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 78, name = "B.Tech Cyber Security", parentId = 67, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 79, name = "MCA",                   parentId = 67, type = "LEAF", colorHex = "#8B5CF6")
        ))

        // B.Tech children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 80, name = "Software Engineering",  parentId = 9, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 81, name = "Hardware & Embedded",   parentId = 9, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 82, name = "Civil & Infrastructure", parentId = 9, type = "BRANCH", colorHex = "#6366F1"),
            CareerNodeEntity(id = 83, name = "Emerging Tech",          parentId = 9, type = "BRANCH", colorHex = "#6366F1")
        ))

        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 84, name = "Full Stack Developer", parentId = 80, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 85, name = "Mobile App Developer", parentId = 80, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 86, name = "DevOps Engineer",      parentId = 80, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 87, name = "Cloud Architect",      parentId = 80, type = "LEAF", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 88, name = "Embedded Systems Engg", parentId = 81, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 89, name = "VLSI Design",           parentId = 81, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 90, name = "IoT Engineer",          parentId = 81, type = "LEAF", colorHex = "#F59E0B"),
            CareerNodeEntity(id = 91, name = "AI / ML Engineer",      parentId = 83, type = "LEAF", colorHex = "#EC4899"),
            CareerNodeEntity(id = 92, name = "Blockchain Developer",  parentId = 83, type = "LEAF", colorHex = "#EC4899"),
            CareerNodeEntity(id = 93, name = "AR / VR Developer",     parentId = 83, type = "LEAF", colorHex = "#EC4899"),
            CareerNodeEntity(id = 94, name = "Quantum Computing",     parentId = 83, type = "LEAF", colorHex = "#EC4899")
        ))

        // BCA children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 95, name = "Software Development", parentId = 6, type = "BRANCH", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 96, name = "Data & Analytics",     parentId = 6, type = "BRANCH", colorHex = "#3B82F6"),
            CareerNodeEntity(id = 97, name = "Cyber Security",       parentId = 6, type = "BRANCH", colorHex = "#3B82F6")
        ))

        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 98,  name = "MCA",                    parentId = 95, type = "LEAF", colorHex = "#6366F1"),
            CareerNodeEntity(id = 99,  name = "Web Developer",          parentId = 95, type = "LEAF", colorHex = "#6366F1"),
            CareerNodeEntity(id = 100, name = "Data Analyst",           parentId = 96, type = "LEAF", colorHex = "#6366F1"),
            CareerNodeEntity(id = 101, name = "Business Intelligence",  parentId = 96, type = "LEAF", colorHex = "#6366F1"),
            CareerNodeEntity(id = 102, name = "Security Analyst",       parentId = 97, type = "LEAF", colorHex = "#6366F1"),
            CareerNodeEntity(id = 103, name = "Ethical Hacking",        parentId = 97, type = "LEAF", colorHex = "#6366F1")
        ))

        // MBA children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 104, name = "Finance",          parentId = 10, type = "BRANCH", colorHex = "#DC2626"),
            CareerNodeEntity(id = 105, name = "Marketing",        parentId = 10, type = "BRANCH", colorHex = "#DC2626"),
            CareerNodeEntity(id = 106, name = "Human Resources",  parentId = 10, type = "BRANCH", colorHex = "#DC2626"),
            CareerNodeEntity(id = 107, name = "Operations & SCM", parentId = 10, type = "BRANCH", colorHex = "#DC2626")
        ))

        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 108, name = "Investment Banker",    parentId = 104, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 109, name = "Financial Analyst",    parentId = 104, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 110, name = "Risk Manager",         parentId = 104, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 111, name = "Digital Marketer",     parentId = 105, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 112, name = "Brand Manager",        parentId = 105, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 113, name = "Product Manager",      parentId = 105, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 114, name = "HR Business Partner",  parentId = 106, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 115, name = "Talent Acquisition",   parentId = 106, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 116, name = "Supply Chain Analyst", parentId = 107, type = "LEAF", colorHex = "#EF4444"),
            CareerNodeEntity(id = 117, name = "Logistics Manager",    parentId = 107, type = "LEAF", colorHex = "#EF4444")
        ))

        // B.Ed children
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 118, name = "Primary Education",   parentId = 11, type = "BRANCH", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 119, name = "Secondary Education", parentId = 11, type = "BRANCH", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 120, name = "Special Education",   parentId = 11, type = "BRANCH", colorHex = "#7C3AED")
        ))

        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 121, name = "Primary School Teacher", parentId = 118, type = "LEAF", colorHex = "#A78BFA"),
            CareerNodeEntity(id = 122, name = "School Principal",       parentId = 118, type = "LEAF", colorHex = "#A78BFA"),
            CareerNodeEntity(id = 123, name = "High School Teacher",    parentId = 119, type = "LEAF", colorHex = "#A78BFA"),
            CareerNodeEntity(id = 124, name = "College Lecturer",       parentId = 119, type = "LEAF", colorHex = "#A78BFA"),
            CareerNodeEntity(id = 125, name = "Special Needs Educator", parentId = 120, type = "LEAF", colorHex = "#A78BFA")
        ))

        seedPhaseANodes(nodeDao)
        seedMissingCourses(nodeDao)
        seedMissingRootChildren(nodeDao)
        seedDetails(detailDao)
        seedPhaseADetails(detailDao)
        seedMissingCourseDetails(detailDao)
        seedNewNodeDetails(detailDao)
    }

    private suspend fun seedPhaseANodes(nodeDao: CareerNodeDao) {
        // ── PHASE A NEW ROOT NODES ────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 130, name = "Government Jobs",    parentId = null, type = "ROOT", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 131, name = "Defence & Military", parentId = null, type = "ROOT", colorHex = "#065F46"),
            CareerNodeEntity(id = 132, name = "Sports & Fitness",   parentId = null, type = "ROOT", colorHex = "#B45309"),
            CareerNodeEntity(id = 133, name = "Entrepreneurship",   parentId = null, type = "ROOT", colorHex = "#92400E"),
            CareerNodeEntity(id = 134, name = "Creative Arts",      parentId = null, type = "ROOT", colorHex = "#5B21B6"),
            CareerNodeEntity(id = 135, name = "Healthcare Allied",  parentId = null, type = "ROOT", colorHex = "#047857")
        ))

        // Government Jobs branches
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 140, name = "Civil Services (UPSC)", parentId = 130, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 141, name = "Banking & Finance",     parentId = 130, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 142, name = "Railway Jobs",          parentId = 130, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 143, name = "SSC & State PSC",       parentId = 130, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 144, name = "Govt Teaching Jobs",    parentId = 130, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 145, name = "PSU Jobs",              parentId = 130, type = "BRANCH", colorHex = "#1D4ED8")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 150, name = "IAS (Collector/DM)",      parentId = 140, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 151, name = "IPS (Police Service)",     parentId = 140, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 152, name = "IFS (Foreign Service)",    parentId = 140, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 153, name = "IRS (Revenue Service)",    parentId = 140, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 154, name = "IES (Engineering Service)",parentId = 140, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 155, name = "Bank PO (IBPS/SBI)",      parentId = 141, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 156, name = "Bank Clerk",              parentId = 141, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 157, name = "RBI Grade B Officer",     parentId = 141, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 158, name = "NABARD Officer",          parentId = 141, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 159, name = "RRB NTPC",               parentId = 142, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 160, name = "RRB JE (Junior Engineer)",parentId = 142, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 161, name = "Railway Loco Pilot",      parentId = 142, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 162, name = "Railway Station Master",  parentId = 142, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 163, name = "SSC CGL",                parentId = 143, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 164, name = "SSC CHSL",               parentId = 143, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 165, name = "State PSC Officer",      parentId = 143, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 166, name = "Police Constable / SI",  parentId = 143, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 167, name = "KVS / NVS Teacher",      parentId = 144, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 168, name = "State Govt Teacher",     parentId = 144, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 169, name = "University Professor",   parentId = 144, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 170, name = "ISRO Scientist",         parentId = 145, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 171, name = "DRDO Scientist",         parentId = 145, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 172, name = "BHEL Engineer",          parentId = 145, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 173, name = "ONGC Engineer",          parentId = 145, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 174, name = "NTPC Engineer",          parentId = 145, type = "LEAF", colorHex = "#2563EB")
        ))

        // Defence & Military
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 180, name = "Indian Army",         parentId = 131, type = "BRANCH", colorHex = "#065F46"),
            CareerNodeEntity(id = 181, name = "Indian Navy",         parentId = 131, type = "BRANCH", colorHex = "#065F46"),
            CareerNodeEntity(id = 182, name = "Indian Air Force",    parentId = 131, type = "BRANCH", colorHex = "#065F46"),
            CareerNodeEntity(id = 183, name = "Paramilitary Forces", parentId = 131, type = "BRANCH", colorHex = "#065F46")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 184, name = "Army Officer (NDA/CDS)", parentId = 180, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 185, name = "Army Soldier (GD)",      parentId = 180, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 186, name = "Army Technical Entry",   parentId = 180, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 187, name = "Navy Officer (NDA/CDS)", parentId = 181, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 188, name = "Navy Sailor Entry",      parentId = 181, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 189, name = "Air Force Officer",      parentId = 182, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 190, name = "Air Force Airman",       parentId = 182, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 191, name = "CRPF / BSF Officer",     parentId = 183, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 192, name = "CISF Officer",           parentId = 183, type = "LEAF", colorHex = "#059669")
        ))

        // Sports & Fitness
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 200, name = "Professional Sports", parentId = 132, type = "BRANCH", colorHex = "#B45309"),
            CareerNodeEntity(id = 201, name = "Sports Science",      parentId = 132, type = "BRANCH", colorHex = "#B45309"),
            CareerNodeEntity(id = 202, name = "Fitness & Wellness",  parentId = 132, type = "BRANCH", colorHex = "#B45309"),
            CareerNodeEntity(id = 203, name = "Sports Management",   parentId = 132, type = "BRANCH", colorHex = "#B45309")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 204, name = "Cricket Player",          parentId = 200, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 205, name = "Football Player",         parentId = 200, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 206, name = "Badminton Player",        parentId = 200, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 207, name = "Chess Player",            parentId = 200, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 208, name = "Athletics / Track",       parentId = 200, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 209, name = "Sports Physiotherapist",  parentId = 201, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 210, name = "Sports Nutritionist",     parentId = 201, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 211, name = "Sports Psychologist",     parentId = 201, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 212, name = "Personal Trainer",        parentId = 202, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 213, name = "Yoga Instructor",         parentId = 202, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 214, name = "Sports Coach",            parentId = 202, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 215, name = "Sports Event Manager",    parentId = 203, type = "LEAF", colorHex = "#D97706"),
            CareerNodeEntity(id = 216, name = "Sports Commentator",      parentId = 203, type = "LEAF", colorHex = "#D97706")
        ))

        // Entrepreneurship
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 220, name = "Tech Startup",           parentId = 133, type = "BRANCH", colorHex = "#92400E"),
            CareerNodeEntity(id = 221, name = "Social Entrepreneurship",parentId = 133, type = "BRANCH", colorHex = "#92400E"),
            CareerNodeEntity(id = 222, name = "Business & Trade",       parentId = 133, type = "BRANCH", colorHex = "#92400E"),
            CareerNodeEntity(id = 223, name = "Creative Business",      parentId = 133, type = "BRANCH", colorHex = "#92400E"),
            CareerNodeEntity(id = 224, name = "Freelancing",            parentId = 133, type = "BRANCH", colorHex = "#92400E")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 225, name = "App / SaaS Founder",   parentId = 220, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 226, name = "EdTech Startup",       parentId = 220, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 227, name = "Fintech Startup",      parentId = 220, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 228, name = "NGO / Non-Profit",     parentId = 221, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 229, name = "Social Impact Startup",parentId = 221, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 230, name = "Import / Export",      parentId = 222, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 231, name = "Retail / E-Commerce",  parentId = 222, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 232, name = "Manufacturing Unit",   parentId = 222, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 233, name = "YouTuber / Creator",   parentId = 223, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 234, name = "Photographer",         parentId = 223, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 235, name = "Food Business",        parentId = 223, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 236, name = "Freelance Developer",  parentId = 224, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 237, name = "Freelance Designer",   parentId = 224, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 238, name = "Freelance Writer",     parentId = 224, type = "LEAF", colorHex = "#B45309")
        ))

        // Creative Arts
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 250, name = "Visual Arts",          parentId = 134, type = "BRANCH", colorHex = "#5B21B6"),
            CareerNodeEntity(id = 251, name = "Performing Arts",      parentId = 134, type = "BRANCH", colorHex = "#5B21B6"),
            CareerNodeEntity(id = 252, name = "Music",                parentId = 134, type = "BRANCH", colorHex = "#5B21B6"),
            CareerNodeEntity(id = 253, name = "Writing & Literature", parentId = 134, type = "BRANCH", colorHex = "#5B21B6")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 254, name = "Graphic Designer",    parentId = 250, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 255, name = "Illustrator / Artist",parentId = 250, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 256, name = "Fashion Designer",    parentId = 250, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 257, name = "Interior Designer",   parentId = 250, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 258, name = "Actor / Actress",     parentId = 251, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 259, name = "Theatre Artist",      parentId = 251, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 260, name = "Dancer",              parentId = 251, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 261, name = "Film Director",       parentId = 251, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 262, name = "Musician / Singer",   parentId = 252, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 263, name = "Music Producer",      parentId = 252, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 264, name = "Sound Engineer",      parentId = 252, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 265, name = "Author / Novelist",   parentId = 253, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 266, name = "Content Writer",      parentId = 253, type = "LEAF", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 267, name = "Screenwriter",        parentId = 253, type = "LEAF", colorHex = "#7C3AED")
        ))

        // Healthcare Allied
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 270, name = "Nursing",             parentId = 135, type = "BRANCH", colorHex = "#047857"),
            CareerNodeEntity(id = 271, name = "Therapy & Rehab",     parentId = 135, type = "BRANCH", colorHex = "#047857"),
            CareerNodeEntity(id = 272, name = "Diagnostics",         parentId = 135, type = "BRANCH", colorHex = "#047857"),
            CareerNodeEntity(id = 273, name = "Alternative Medicine",parentId = 135, type = "BRANCH", colorHex = "#047857")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 274, name = "B.Sc Nursing",           parentId = 270, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 275, name = "ANM / GNM Nurse",        parentId = 270, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 276, name = "Nurse Practitioner",     parentId = 270, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 277, name = "Physiotherapist (BPT)",  parentId = 271, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 278, name = "Occupational Therapist", parentId = 271, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 279, name = "Speech Therapist",       parentId = 271, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 280, name = "Medical Lab Technician", parentId = 272, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 281, name = "Radiographer / X-Ray",   parentId = 272, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 282, name = "ECG Technician",         parentId = 272, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 283, name = "Naturopath",             parentId = 273, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 284, name = "Homeopathy (BHMS)",      parentId = 273, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 285, name = "Unani Medicine (BUMS)",  parentId = 273, type = "LEAF", colorHex = "#059669")
        ))
    }

    private suspend fun seedDetails(dao: CareerDetailDao) {
        if (dao.getCount() > 0) return

        data class Bundle(
            val nodeId: Int, val desc: String, val next: String,
            val skills: List<String>,
            val certs: List<Pair<String, String>>,
            val projects: List<Pair<String, String>>
        )

        val bundles = listOf(
            Bundle(20,"MBBS is a 5.5-year medical degree training students to become doctors. Study anatomy, physiology, pharmacology, surgery and clinical medicine. After MBBS specialise in MD or MS.",
                "Clear NEET UG with 600+ score. Complete 5.5 years + internship. Then NEET PG for specialisation.",
                listOf("Anatomy & Physiology","Clinical Diagnosis","Pharmacology","Surgery Basics","Patient Communication","Medical Research"),
                listOf("NEET UG" to "NTA India","USMLE Step 1" to "NBME USA","FMGE" to "NBE India"),
                listOf("Health Camp" to "Organise free health checkup in your village.","Case Portfolio" to "Document 20 real patient case studies.","Research Paper" to "Write research on a common local disease.")),

            Bundle(21,"BDS is a 5-year Dentistry degree. Dentists diagnose oral problems, do fillings, root canals, extractions and cosmetic procedures. Dental tourism is booming in India.",
                "Clear NEET UG. After BDS do MDS for specialisation in orthodontics or oral surgery. Opening own clinic costs 5-15 lakhs.",
                listOf("Oral Anatomy","Dental Radiology","Tooth Filling & Extraction","Orthodontics","Patient Management","Sterilisation"),
                listOf("NEET UG" to "NTA India","MDS Entrance" to "NEET MDS","Dental License" to "DCI India"),
                listOf("Dental Camp" to "Free dental checkup in school.","Oral Survey" to "Survey oral hygiene of 50 people.","Case Presentation" to "Present 5 complex dental cases.")),

            Bundle(22,"B.Pharm is a 4-year pharmacy degree covering drug composition, manufacturing and dispensing. Pharmacists work in hospitals, retail chemists, pharma companies and research labs.",
                "After B.Pharm pursue M.Pharm for specialisation or crack Drug Inspector exam for government job.",
                listOf("Pharmacology","Pharmaceutical Chemistry","Drug Formulation","Hospital Pharmacy","Quality Control","Clinical Research"),
                listOf("GPAT" to "NTA India","Drug Inspector Exam" to "State PSC","M.Pharm Entrance" to "University"),
                listOf("Drug Interaction Study" to "Study interactions between 10 common drugs.","Inventory System" to "Build pharmacy inventory management software.","Generic vs Brand" to "Compare generic and branded medicines.")),

            Bundle(23,"Biotechnology B.Sc combines biology and technology to develop medicines, vaccines, GMO crops and diagnostic tools. One of fastest growing fields globally.",
                "After B.Sc Biotech pursue M.Sc Biotechnology or MBA. Top recruiters: Biocon, Dr Reddy's, CSIR labs.",
                listOf("Molecular Biology","Genetic Engineering","Cell Culture","Bioinformatics","PCR Techniques","Research Methods"),
                listOf("GATE Biotech" to "IIT/IISC","CSIR NET" to "CSIR India","DBT JRF" to "Dept of Biotechnology"),
                listOf("DNA Extraction" to "Extract DNA from banana in home lab.","Research Review" to "Write review on CRISPR gene editing.","Bioinformatics" to "Analyse protein sequences using online tools.")),

            Bundle(24,"B.Tech CSE teaches programming, data structures, algorithms, databases and software development. CSE graduates are most in-demand professionals globally with top salaries.",
                "Build GitHub projects. Prepare for campus placements or GATE. Target Google, Amazon, Infosys, TCS.",
                listOf("Data Structures & Algorithms","Java/Python/C++","System Design","Database Management","OS & Networks","Software Engineering"),
                listOf("Oracle Java Cert" to "Oracle","AWS Solutions Architect" to "Amazon","Google Cloud Associate" to "Google"),
                listOf("Result Portal" to "Build full-stack student result system.","Chat App" to "Real-time chat using WebSockets.","Library System" to "Desktop app for library management.")),

            Bundle(25,"AI & ML engineers build intelligent systems that learn from data for recommendations, image recognition, NLP and autonomous vehicles. Highest paying tech field today.",
                "Learn Python, TensorFlow and Statistics. Build Kaggle projects. Target MAANG companies as ML Engineer.",
                listOf("Python Programming","TensorFlow/PyTorch","Statistics","Deep Learning","Data Preprocessing","Model Deployment"),
                listOf("Google ML Course" to "Google","Deep Learning Spec" to "Coursera/DeepLearning.AI","AWS ML Specialty" to "Amazon"),
                listOf("Digit Classifier" to "Build neural network to identify handwritten digits.","Movie Recommender" to "Build collaborative filtering system.","Chatbot" to "Build NLP-based FAQ chatbot.")),

            Bundle(26,"Cyber Security professionals protect computers, networks and data from hackers. Work as ethical hackers, security analysts, SOC analysts and forensic investigators.",
                "Get CEH or CompTIA Security+ certified. Practice on TryHackMe and HackTheBox. Target banks, IT companies and CERT-IN.",
                listOf("Network Security","Ethical Hacking","Linux & Bash","Python Scripting","SIEM Tools","Vulnerability Assessment"),
                listOf("CompTIA Security+" to "CompTIA","CEH" to "EC-Council","OSCP" to "Offensive Security"),
                listOf("CTF Challenges" to "Solve 10 CTF challenges on TryHackMe.","Network Scanner" to "Build Python tool to scan open ports.","Password Audit" to "Study hash cracking techniques ethically.")),

            Bundle(27,"Data Scientists extract insights from large datasets using statistics, ML and visualisation. Work in e-commerce, finance, healthcare and sports analytics. Top paying career in India.",
                "Master Python, SQL and Power BI. Build Kaggle portfolio. Join analytics firms like Mu Sigma or Fractal Analytics.",
                listOf("Python (Pandas/NumPy)","SQL & NoSQL","Machine Learning","Power BI/Tableau","Statistics","Big Data Spark"),
                listOf("IBM Data Science Cert" to "Coursera/IBM","Azure Data Scientist" to "Microsoft","Tableau Specialist" to "Tableau"),
                listOf("Churn Prediction" to "Predict customer churn using ML.","Sales Dashboard" to "Build interactive Power BI report.","Sentiment Analysis" to "Analyse Twitter sentiment on any topic.")),

            Bundle(28,"Mechanical Engineering covers design, manufacturing, thermodynamics and robotics. Engineers design machines, engines, HVAC systems and industrial equipment. Evergreen demand.",
                "Get placed in automobile, aerospace or manufacturing. Pursue M.Tech in Thermal/Design or PSU jobs via GATE.",
                listOf("Engineering Drawing & CAD","Thermodynamics","Fluid Mechanics","Manufacturing","Machine Design","Robotics"),
                listOf("GATE Mechanical" to "IIT/IISC","CAD Certification" to "Autodesk","Six Sigma" to "ASQ"),
                listOf("Windmill Model" to "Build a small working windmill model.","CAD Design" to "Design mechanical component in SolidWorks.","Heat Transfer" to "Experiment on thermal conductivity of materials.")),

            Bundle(29,"Civil Engineering involves designing roads, bridges, dams, buildings and water supply systems. Essential for India's growing infrastructure development with huge government investment.",
                "Join construction companies or government PWD. Crack GATE for PSU jobs. You can start your own construction consultancy.",
                listOf("Structural Analysis","AutoCAD & Revit","Concrete Technology","Soil Mechanics","Project Management","Surveying"),
                listOf("GATE Civil" to "IIT/IISC","AutoCAD Certified" to "Autodesk","PMP" to "PMI"),
                listOf("Bridge Model" to "Build spaghetti bridge and test load capacity.","Site Layout" to "Draw complete layout plan for small building.","Soil Test" to "Perform soil bearing capacity test.")),

            Bundle(30,"Electrical Engineering covers power systems, motors, transformers and renewable energy. Work in power plants, ISRO, DRDO, Siemens, L&T and electricity boards.",
                "Crack GATE for BHEL, NTPC, PGCIL jobs. Pursue M.Tech in Power Systems or MBA in Energy Management.",
                listOf("Circuit Analysis","Power Systems","Control Systems","Electric Machines","PLC Programming","Renewable Energy"),
                listOf("GATE Electrical" to "IIT/IISC","Electrical Safety" to "NSDC","PLC Programming" to "Siemens"),
                listOf("Solar Charger" to "Build solar powered phone charger.","Home Automation" to "Control appliances using Arduino.","Motor Controller" to "Build DC motor speed controller.")),

            Bundle(31,"Aerospace Engineering designs aircraft, spacecraft, satellites and missiles. Work at ISRO, HAL, DRDO, Boeing, Airbus or NASA. Most prestigious engineering branch.",
                "Join ISRO through campus recruitment or ICRB exam. Pursue M.Tech in Aerospace. Strong Math and Physics essential.",
                listOf("Aerodynamics","Propulsion Systems","Structural Mechanics","Flight Dynamics","CFD Analysis","MATLAB/ANSYS"),
                listOf("GATE Aerospace" to "IIT/IISC","ICRB Scientist Exam" to "ISRO","CFD Simulation" to "ANSYS"),
                listOf("RC Plane" to "Build and fly remote-controlled airplane.","Rocket Model" to "Build water-powered bottle rocket.","CFD Analysis" to "Simulate airflow around airfoil using ANSYS.")),

            Bundle(32,"B.Sc Physics studies mechanics, optics, electromagnetism, quantum physics and nuclear physics. Physicists work in research labs, ISRO, DRDO and teaching.",
                "Pursue M.Sc Physics then PhD. Switch to Data Science or Actuarial Science. JRF fellowship via CSIR NET.",
                listOf("Classical Mechanics","Electromagnetism","Quantum Physics","Optics","Nuclear Physics","Mathematical Physics"),
                listOf("CSIR NET Physics" to "CSIR India","JEST" to "DAE India","GATE Physics" to "IIT/IISC"),
                listOf("Telescope" to "Build simple refracting telescope.","Optics Experiments" to "Demonstrate refraction and diffraction.","Data Analysis" to "Analyse physics data using Python.")),

            Bundle(33,"B.Sc Chemistry studies atomic structure, chemical reactions, organic and analytical chemistry. Chemists work in pharma, food science, petrochemicals and environmental science.",
                "Pursue M.Sc then PhD or MBA in Pharma. Target CSIR labs, Dr Reddy's, Cipla, ONGC or teaching.",
                listOf("Organic Chemistry","Inorganic Chemistry","Analytical Techniques","Spectroscopy","Industrial Chemistry","Lab Safety"),
                listOf("CSIR NET Chemistry" to "CSIR India","GATE Chemistry" to "IIT/IISC","M.Sc Entrance" to "University"),
                listOf("Soap Making" to "Make herbal soap using saponification.","Water Quality Test" to "Test local water samples for contamination.","Titration" to "Perform acid-base titration and calculate molarity.")),

            Bundle(34,"B.Sc Mathematics covers calculus, algebra, statistics, number theory and differential equations. Mathematicians work in finance, data science, actuarial science and cryptography.",
                "Pursue M.Sc Mathematics or Data Science. Crack CSIR NET for research fellowship. High demand in banking, insurance and IT.",
                listOf("Calculus & Analysis","Linear Algebra","Statistics & Probability","Number Theory","Differential Equations","Python/MATLAB"),
                listOf("CSIR NET Maths" to "CSIR India","Actuarial Exam" to "IAI India","GATE Mathematics" to "IIT/IISC"),
                listOf("Census Analysis" to "Analyse real census data and find patterns.","Cryptography" to "Implement RSA encryption in Python.","Math Visualiser" to "Create 3D graphs using Python Matplotlib.")),

            Bundle(38,"CA is India's most prestigious professional qualification. CAs handle auditing, income tax, GST, company law and financial advisory. Salary: 7 to 40+ lakhs per annum.",
                "Register with ICAI after 12th. Clear Foundation then Intermediate then Final. Complete 3 years articleship. Average 5 years total.",
                listOf("Financial Accounting","Taxation (Income Tax & GST)","Auditing & Assurance","Corporate Law","Cost Accounting","Financial Analysis"),
                listOf("CA Foundation" to "ICAI","CA Intermediate" to "ICAI","CA Final" to "ICAI"),
                listOf("GST Filing" to "Practice filing monthly GSTR-1 and GSTR-3B.","Tax Audit" to "Prepare mock Form 3CA/3CD for small firm.","Financial Model" to "Build DCF valuation model for listed company.")),

            Bundle(39,"CMA focuses on cost accounting, management accounting and financial management. CMAs work in manufacturing, banks and government enterprises as cost auditors.",
                "Register with ICMAI. Clear Foundation then Intermediate then Final. Recognized for government cost audits.",
                listOf("Cost Accounting","Management Accounting","Financial Management","Taxation","Auditing","Strategic Management"),
                listOf("CMA Foundation" to "ICMAI","CMA Intermediate" to "ICMAI","CMA Final" to "ICMAI"),
                listOf("Cost Sheet" to "Prepare cost sheet for manufacturing unit.","Budget Analysis" to "Analyse budget vs actual for a company.","Pricing Strategy" to "Suggest pricing using cost-plus method.")),

            Bundle(40,"B.Com Hons is a 3-year degree in accounting, business law, economics and finance. Foundation for CA, MBA and corporate finance careers with good placement opportunities.",
                "After B.Com pursue CA, MBA, CMA or M.Com. Good for banking, finance and Big 4 accounting firms.",
                listOf("Financial Accounting","Business Law","Economics","Income Tax","Auditing","Cost Accounting"),
                listOf("CA Foundation" to "ICAI","CFA Level 1" to "CFA Institute","MBA CAT" to "IIM"),
                listOf("Annual Report" to "Analyse annual report of a listed company.","GST Calculator" to "Build Excel tool to calculate GST.","Business Plan" to "Write complete business plan for startup.")),

            Bundle(41,"BBA is a 3-year management degree covering marketing, HR, finance, operations and entrepreneurship. Ideal foundation for MBA and business careers.",
                "After BBA pursue MBA from top B-school via CAT, XAT or GMAT. Can also join marketing, sales or operations roles directly.",
                listOf("Business Management","Marketing Basics","Financial Accounting","Human Resources","Operations Management","Business Communication"),
                listOf("CAT Exam" to "IIM","Google Digital Marketing" to "Google","IELTS/TOEFL" to "British Council"),
                listOf("Case Study" to "Analyse real company case like Zomato IPO.","Marketing Campaign" to "Create complete digital marketing campaign.","Startup Pitch" to "Present a startup pitch to classmates.")),

            Bundle(42,"MBA Finance specialises in corporate finance, investment banking, financial markets and risk management. One of highest paying MBA specialisations.",
                "Crack CAT/XAT for top IIMs. Target finance roles in investment banks, consulting firms. CFA alongside MBA is highly valuable.",
                listOf("Corporate Finance","Investment Analysis","Financial Modelling","Risk Management","Portfolio Management","Derivatives"),
                listOf("CFA Level 1" to "CFA Institute","FRM" to "GARP","Bloomberg Cert" to "Bloomberg"),
                listOf("Stock Portfolio" to "Manage virtual stock portfolio for 3 months.","Company Valuation" to "Do DCF and comparable company analysis.","Market Analysis" to "Write weekly stock market analysis for 1 month.")),

            Bundle(43,"B.Sc Economics studies micro and macroeconomics, econometrics, development economics and policy. Economists work in banks, government, consulting, research and international organisations.",
                "Pursue M.Sc Economics or MBA. Crack IES exam for government economist. Good for UPSC preparation.",
                listOf("Microeconomics","Macroeconomics","Econometrics","Statistics","Development Economics","Policy Analysis"),
                listOf("IES Exam" to "UPSC India","M.Sc Economics" to "DSE/JNU","GATE Economics" to "IIT/IISC"),
                listOf("Economic Survey" to "Summarise key findings from India Economic Survey.","Market Study" to "Study price changes of vegetables in local market.","Policy Paper" to "Write policy recommendation on current economic issue.")),

            Bundle(44,"Investment Bankers advise on mergers, acquisitions, IPOs and raising capital. Work at Goldman Sachs, Morgan Stanley, JP Morgan and Kotak IB. Extraordinary salaries.",
                "Pursue MBA Finance from IIM/XLRI. Intern at leading IB firm. Network at finance events. CFA dramatically increases credibility.",
                listOf("Financial Modelling DCF/LBO","Valuation Methods","Capital Markets","M&A Process","Excel & PowerPoint","Pitch Book Creation"),
                listOf("CFA Level 1-3" to "CFA Institute","FMVA" to "CFI","Bloomberg Concepts" to "Bloomberg"),
                listOf("Pitch Book" to "Create full investment banking pitch book.","M&A Model" to "Build merger model with synergies in Excel.","IPO Analysis" to "Analyse upcoming IPO and value the company.")),

            Bundle(50,"BA Political Science studies government systems, international relations, political theory and public policy. Graduates work in civil services, journalism, NGOs and diplomacy.",
                "Pursue MA Political Science then crack UPSC Civil Services for IAS/IFS. Also good for law, journalism and public policy.",
                listOf("Political Theory","Indian Constitution","International Relations","Public Policy","Research Methods","Current Affairs"),
                listOf("UPSC CSE" to "UPSC India","MA Entrance" to "JNU/DU","UGC NET" to "NTA India"),
                listOf("Policy Analysis" to "Analyse any government scheme and its impact.","Mock Parliament" to "Organise mock parliament debate in college.","Election Study" to "Study voting patterns in a recent election.")),

            Bundle(51,"BA Psychology studies human behaviour, mental processes, emotions, personality and mental disorders. Psychologists work as counsellors, HR professionals, school counsellors and researchers.",
                "Pursue MA/M.Sc Psychology then RCI registration for clinical practice. Excellent career in corporate HR, therapy and education.",
                listOf("Abnormal Psychology","Cognitive Psychology","Counselling Techniques","Research Methods","Child Psychology","Personality Assessment"),
                listOf("RCI Registration" to "RCI India","MA Psychology" to "University","UGC NET Psychology" to "NTA India"),
                listOf("Stress Survey" to "Conduct survey on student stress levels.","Case Study" to "Write psychological case study on fictional client.","Mental Health Workshop" to "Create workshop on anxiety management techniques.")),

            Bundle(52,"BA Sociology studies society, social institutions, culture, inequality, caste, gender and urbanisation. Sociologists work in NGOs, government policy, social work and journalism.",
                "Pursue MA Sociology then PhD or crack UPSC for IAS. Also good for MSW and NGO careers.",
                listOf("Social Theory","Research Methods","Indian Society","Gender Studies","Urban Sociology","Caste & Inequality"),
                listOf("UGC NET Sociology" to "NTA India","MA Entrance" to "JNU/Hyderabad Univ","UPSC CSE" to "UPSC India"),
                listOf("Village Survey" to "Study social issues in village and write report.","Documentary" to "Make short documentary on a social issue.","Policy Brief" to "Write policy brief on social problem in your city.")),

            Bundle(53,"UI/UX Designers create beautiful and intuitive digital interfaces for apps and websites. They research user needs, create wireframes and test usability. Extremely high demand in startups.",
                "Build Figma portfolio with 5+ case studies. Learn user research and design systems. Apply to product companies or freelance.",
                listOf("Figma/Adobe XD","User Research","Wireframing","Prototyping","Design Systems","Usability Testing"),
                listOf("Google UX Design Cert" to "Google/Coursera","Adobe Certified Pro" to "Adobe","IDF UX Design" to "Interaction Design Foundation"),
                listOf("App Redesign" to "Redesign Zomato with better UX and justify decisions.","Design System" to "Build complete UI component library in Figma.","Usability Test" to "Conduct usability testing with 5 users and document.")),

            Bundle(54,"Mass Communication covers journalism, public relations, advertising, film and digital media. Graduates work as journalists, content creators, PR managers and social media managers.",
                "Intern at news channels, digital media or PR firms. Build portfolio of articles, videos or campaigns. Top employers: NDTV, Times Group, Ogilvy.",
                listOf("News Writing & Reporting","Video Production","Public Relations","Social Media Management","Advertising","Media Law"),
                listOf("PG Diploma Journalism" to "IIMC","Google Digital Marketing" to "Google","PR Certification" to "PRCI India"),
                listOf("Campus Blog" to "Start news blog covering college events.","Short Film" to "Produce 5-minute short documentary film.","PR Campaign" to "Design complete PR campaign for local NGO.")),

            Bundle(55,"Animation & VFX professionals create animated films, game graphics and visual effects for movies and advertising. High demand in Bollywood, OTT platforms and gaming companies.",
                "Build strong showreel with 3D animation and VFX projects. Join studios like Pixion, Prime Focus or work for Hollywood productions.",
                listOf("3D Modelling (Blender/Maya)","Animation Principles","VFX Compositing (After Effects)","Motion Graphics","Storyboarding","Texturing & Lighting"),
                listOf("Adobe Certified Expert" to "Adobe","Autodesk Maya Cert" to "Autodesk","FX PhD" to "Foundry"),
                listOf("3D Character" to "Model and rig 3D character in Blender.","Short Animation" to "Create 30-second animated short film.","VFX Shot" to "Add VFX to real video using After Effects.")),

            Bundle(56,"LLB is a 3-year law degree for graduates to enter legal profession. Lawyers represent clients in civil and criminal courts, draft contracts and provide legal advisory.",
                "Enrol with State Bar Council after LLB. Start as junior lawyer under senior advocate. Specialise in corporate, criminal or family law.",
                listOf("Contract Law","Criminal Law","Constitutional Law","Legal Research","Court Procedure","Legal Drafting"),
                listOf("Bar Council Enrolment" to "State Bar Council","LLM Entrance" to "University","Judicial Services" to "State PSC"),
                listOf("Moot Court" to "Argue case in national moot court competition.","Contract Drafting" to "Draft employment and rental agreements.","Legal Aid" to "Provide free legal advice under faculty supervision.")),

            Bundle(57,"BA LLB is an integrated 5-year law degree combining arts and law. Preferred route for judiciary, corporate law or civil services with legal background.",
                "Join National Law Universities through CLAT. After BA LLB pursue LLM or join top law firms like AZB & Partners or Khaitan & Co.",
                listOf("Constitutional Law","Contract & Tort Law","Criminal Law","International Law","Legal Research","Moot Court Skills"),
                listOf("CLAT" to "NLU Consortium","Judicial Services" to "State PSC","Bar Council Enrolment" to "State Bar Council"),
                listOf("CLAT Practice" to "Solve 30 CLAT mock papers.","Case Analysis" to "Analyse 10 landmark Supreme Court judgements.","Internship Report" to "Write report after interning at a law firm.")),

            Bundle(60,"Electrician Trade is a 2-year ITI course to install, maintain and repair electrical wiring, equipment and machines. Electricians are always in demand in construction and factories.",
                "Get Wireman License from State Electricity Board. Work in construction, factories or start electrical contracting business.",
                listOf("Electrical Wiring","Motor Winding","Panel Board Installation","Earthing & Safety","Electrical Diagrams","Meter Reading"),
                listOf("Wireman License" to "State Electricity Board","Apprenticeship" to "NCVT India","CPWD License" to "CPWD"),
                listOf("House Wiring" to "Wire a complete model house with switches and fan.","Motor Rewinding" to "Rewind a single phase motor.","Solar Wiring" to "Install solar panel with battery backup.")),

            Bundle(61,"Fitter Trade is a 2-year ITI course to assemble, fit and maintain mechanical equipment. Fitters work in factories, railways, defence and manufacturing industries.",
                "After ITI Fitter join railway apprenticeship (RRB), defence units or large factories like L&T, BHEL. Can start fabrication workshop.",
                listOf("Blueprint Reading","Fitting & Assembly","Lathe Machine Operation","Welding Basics","Measurement Tools","Maintenance & Repair"),
                listOf("NCVT Certificate" to "NCVT India","RRB Apprentice" to "Indian Railways","BHEL Apprentice" to "BHEL India"),
                listOf("Metal Bracket" to "Fabricate metal support bracket using workshop tools.","Machine Assembly" to "Disassemble and reassemble a gear box.","Tolerance Study" to "Measure and verify dimensions of machined parts.")),

            Bundle(62,"Welder Trade is a 1-2 year ITI course in gas welding, arc welding, MIG/TIG welding and pipe welding. High demand in shipbuilding, construction, oil & gas. Gulf jobs pay 2-5 lakhs per month.",
                "Work in shipyards, pipeline projects, automobile companies. Overseas welding jobs (Gulf, Europe) offer very high salaries.",
                listOf("Arc Welding SMAW","MIG/MAG Welding","TIG Welding","Gas Welding","Pipe Welding","Welding Safety"),
                listOf("NCVT Welding Cert" to "NCVT India","AWS Certified Welder" to "American Welding Society","ASME Cert" to "ASME"),
                listOf("Steel Gate" to "Weld a complete steel gate for a house.","Pipe Joint" to "Practice TIG welding on stainless steel pipe.","Structural Weld" to "Weld structural frame and test its strength.")),

            Bundle(63,"COPA (Computer Operator & Programming Assistant) is a 1-year ITI course covering MS Office, internet, DTP, Tally and basic programming. Opens data entry and office assistant roles.",
                "After COPA work as computer operator in banks, government offices, schools and BPOs. Can pursue further IT education.",
                listOf("MS Office (Word/Excel/PowerPoint)","Tally ERP","Internet & Email","DTP (PageMaker/CorelDraw)","Data Entry","Basic Programming"),
                listOf("NCVT COPA Cert" to "NCVT India","Tally Certification" to "Tally Solutions","CCC Certificate" to "NIELIT India"),
                listOf("Billing System" to "Create office billing system in Excel.","Newsletter" to "Design 4-page college newsletter using CorelDraw.","Database" to "Create student database using MS Access.")),

            Bundle(68,"B.Tech CSE from top college is India's most prestigious engineering degree. Focus on software development, AI, systems and emerging technologies. Top MNC placements.",
                "Build strong DSA skills. Target Google, Microsoft, Amazon, Flipkart. Crack GATE for M.Tech at IIT.",
                listOf("Data Structures & Algorithms","OOP Programming","Operating Systems","DBMS","Computer Networks","Software Engineering"),
                listOf("GATE CS" to "IIT/IISC","AWS Developer Cert" to "Amazon","Google Cloud Engineer" to "Google"),
                listOf("Compiler" to "Build simple expression evaluator.","OS Scheduler" to "Simulate CPU scheduling algorithms.","Packet Analyser" to "Build basic packet sniffer in Python.")),

            Bundle(69,"B.Tech ECE covers circuit design, embedded systems, VLSI, signal processing and wireless communication. Work in ISRO, DRDO, Qualcomm, Intel and telecom companies.",
                "Pursue M.Tech in VLSI or Signal Processing. Target ISRO, DRDO, Qualcomm, Intel or telecom companies like Ericsson.",
                listOf("Analog & Digital Electronics","Signal & Systems","Microprocessors","Embedded C","VLSI Design","Wireless Communication"),
                listOf("GATE ECE" to "IIT/IISC","Embedded Systems Cert" to "ARM/Keil","CCNA" to "Cisco"),
                listOf("FM Radio" to "Build FM transmitter and receiver circuit.","Line Follower Robot" to "Build autonomous line following robot.","FPGA Project" to "Implement counter on FPGA using VHDL.")),

            Bundle(70,"B.Tech Mechanical opens doors in automobile, aerospace, defence and energy. Strong in thermodynamics, design and manufacturing. Work at Tata Motors, Mahindra, ISRO, DRDO.",
                "Target Tata Motors, Mahindra, ISRO, DRDO, L&T, NTPC. Crack GATE for PSU jobs. MBA opens management roles.",
                listOf("Engineering Mechanics","Thermodynamics","Fluid Mechanics","Manufacturing Technology","CAD/CAM","Industrial Engineering"),
                listOf("GATE Mechanical" to "IIT/IISC","SolidWorks Cert" to "Dassault","Six Sigma" to "ASQ"),
                listOf("Engine Model" to "Build working model of 4-stroke engine.","3D Print" to "Design and 3D print a mechanical component.","Energy Audit" to "Conduct energy audit of college lab equipment.")),

            Bundle(71,"B.Tech Civil ideal for infrastructure development, real estate and government projects. India's massive infrastructure push creates huge demand.",
                "Join L&T, Shapoorji, DLF. Crack GATE for CPWD, PWD, NHAI government jobs. Start architecture or construction consultancy.",
                listOf("Structural Analysis & Design","Concrete & Steel Design","AutoCAD & Revit","Geotechnical Engineering","Transportation Engineering","Project Management"),
                listOf("GATE Civil" to "IIT/IISC","LEED Green Associate" to "USGBC","PMP" to "PMI"),
                listOf("RCC Design" to "Design and calculate RCC beam for given load.","Site Plan" to "Create complete site development plan.","Cost Estimate" to "Estimate construction cost for 2BHK house.")),

            Bundle(72,"MBBS after 12th Science is direct route to becoming a doctor. MBBS graduates can practice independently or specialise further with MD/MS.",
                "Clear NEET with 600+ score. Complete 5.5 years MBBS + internship. Prepare for NEET PG for MD/MS specialisation.",
                listOf("Anatomy","Physiology","Biochemistry","Pathology","Pharmacology","Clinical Medicine & Surgery"),
                listOf("NEET UG" to "NTA India","NEET PG" to "NBE India","USMLE" to "NBME USA"),
                listOf("Clinical Log" to "Maintain log of 50 patient cases during internship.","Research Abstract" to "Submit research abstract to medical conference.","Health Camp" to "Organise free health screening camp.")),

            Bundle(73,"BDS after 12th is 5-year dentistry course. Dentists perform extractions, fillings, root canals and cosmetic procedures. Dental tourism booming in India.",
                "After BDS register with State Dental Council. Open clinic (5-15 lakhs) or pursue MDS specialisation.",
                listOf("Oral Anatomy","Dental Materials","Conservative Dentistry","Orthodontics","Oral Surgery","Prosthodontics"),
                listOf("NEET UG" to "NTA India","MDS Entrance" to "NEET MDS","Dental Council License" to "DCI India"),
                listOf("Dental Camp" to "Conduct free dental checkup in a school.","Oral Survey" to "Survey oral hygiene practices of 50 people.","Case Presentation" to "Present 5 complex dental cases to faculty.")),

            Bundle(74,"BAMS is a 5.5-year Ayurveda medical degree. BAMS doctors practice traditional Indian medicine using herbs, diet, Panchakarma and yoga. Growing globally due to natural health trends.",
                "Register with State Ayurveda Council and open clinic. Pursue MD Ayurveda or work in Ayurveda hospitals and wellness resorts.",
                listOf("Ayurvedic Principles (Tridosha)","Sanskrit Medical Texts","Panchakarma Therapy","Herbal Medicine","Yoga & Naturopathy","Clinical Practice"),
                listOf("NEET UG" to "NTA India","MD Ayurveda" to "AIIA/University","Yoga Instructor Cert" to "Yoga Alliance"),
                listOf("Herbal Garden" to "Grow and identify 20 medicinal plants.","Panchakarma Report" to "Document 10 Panchakarma treatment cases.","Wellness Workshop" to "Conduct Ayurveda lifestyle workshop.")),

            Bundle(75,"B.Pharm after 12th Science is 4-year pharmacy degree. Pharmacists ensure safe dispensing of medicines and work in drug manufacturing quality control.",
                "After B.Pharm pursue M.Pharm or MBA in Pharma. Work at Cipla, Sun Pharma or clinical research organisations.",
                listOf("Pharmaceutical Chemistry","Pharmacology","Pharmaceutics","Drug Regulatory Affairs","Quality Control","Hospital Pharmacy"),
                listOf("GPAT" to "NTA India","Drug Inspector Exam" to "State PSC","Clinical Research Cert" to "ACRP"),
                listOf("Formulation Project" to "Formulate and test a simple tablet preparation.","Drug Study" to "Study pharmacokinetics of a common drug.","QC Audit" to "Perform quality audit checklist for a pharmacy.")),

            Bundle(76,"B.Tech AI & ML is specialised 4-year engineering with highest placement packages in India. Focus on deep learning, computer vision and NLP from day one.",
                "Build GitHub deep learning projects. Intern at AI startups. Target Google AI, Microsoft Research, Amazon Alexa.",
                listOf("Python & R Programming","Deep Learning CNN/RNN","Computer Vision","NLP","MLOps","Cloud AI Services"),
                listOf("TensorFlow Developer" to "Google","AWS ML Specialty" to "Amazon","Azure AI Engineer" to "Microsoft"),
                listOf("Face Recognition" to "Build real-time face recognition system.","Image Captioning" to "Generate image captions using deep learning.","Voice Assistant" to "Build simple voice-controlled assistant.")),

            Bundle(77,"B.Tech Data Science is dedicated 4-year degree combining statistics, programming, ML and big data. Data scientists needed in every industry from healthcare to sports.",
                "Build Kaggle competition wins. Master SQL and Python. Target analytics companies, FAANG or fintech startups.",
                listOf("Statistics & Probability","Python & SQL","Machine Learning","Big Data Hadoop/Spark","Data Visualisation","Feature Engineering"),
                listOf("IBM Data Science Cert" to "Coursera/IBM","Google Data Analytics" to "Google/Coursera","Databricks Cert" to "Databricks"),
                listOf("Customer Segmentation" to "Cluster customers using K-Means algorithm.","Fraud Detection" to "Build credit card fraud detection model.","Sports Analytics" to "Predict IPL match outcomes using ML.")),

            Bundle(78,"B.Tech Cyber Security prepares students for information security, ethical hacking and digital forensics. One of fastest growing and highest paying fields.",
                "Get CEH and OSCP certified. Practice on HackTheBox daily. Target CERT-IN, DRDO, banks and IT security firms.",
                listOf("Network Security","Penetration Testing","Cryptography","Digital Forensics","Malware Analysis","Cloud Security"),
                listOf("CEH" to "EC-Council","CompTIA Security+" to "CompTIA","OSCP" to "Offensive Security"),
                listOf("Vulnerability Scanner" to "Build basic web vulnerability scanner in Python.","CTF Challenge" to "Solve 20 CTF challenges and document solutions.","Security Audit" to "Audit your college website for vulnerabilities.")),

            Bundle(79,"MCA is 2-3 year postgraduate degree in computer science for non-engineering graduates. Equivalent to M.Tech in industry for software development roles.",
                "After MCA apply to product companies, service companies or pursue PhD in CS. Excellent for BCA, B.Sc CS graduates.",
                listOf("Advanced Java/Python","Data Structures & Algorithms","Software Engineering","Database Systems","Web Technologies","Mobile Development"),
                listOf("GATE CS" to "IIT/IISC","Oracle Java Cert" to "Oracle","AWS Developer" to "Amazon"),
                listOf("Hospital System" to "Build complete hospital management application.","Social Network" to "Create social networking app with React.","REST API" to "Build complete REST API with authentication.")),

            Bundle(84,"Full Stack Developers build end-to-end web applications handling both frontend (React) and backend (Node.js). Used by millions of people daily in social media, fintech and e-commerce.",
                "Build 5 complete websites. Master React, Node.js and databases. Freelance on Upwork or join startup. Income 3 LPA to 40+ LPA.",
                listOf("HTML/CSS/JavaScript","React.js","Node.js/Express","MongoDB/PostgreSQL","REST API Design","Git & Deployment"),
                listOf("Meta Front-End Developer" to "Meta/Coursera","Full Stack Web Dev" to "The Odin Project","AWS Cloud Practitioner" to "Amazon"),
                listOf("Portfolio Website" to "Build stunning personal portfolio website.","Blog Platform" to "Create full-stack blog with user authentication.","E-Commerce Site" to "Build online store with payment integration.")),

            Bundle(85,"Mobile App Developers create iOS and Android applications. They work on social media apps, fintech apps, health apps and games. Flutter and React Native are cross-platform choices.",
                "Master Flutter or Kotlin/Swift. Publish 2-3 apps on Play Store. Build portfolio and target startups or freelance clients.",
                listOf("Flutter/React Native","Kotlin (Android)","Swift (iOS)","REST API Integration","Firebase","App Store Deployment"),
                listOf("Google Android Developer" to "Google","Meta React Native" to "Meta/Coursera","Flutter Bootcamp" to "Udemy"),
                listOf("To-Do App" to "Build feature-complete to-do app with local storage.","Weather App" to "Fetch and display weather data using API.","E-Commerce App" to "Build shopping app with cart and payment.")),

            Bundle(86,"DevOps Engineers bridge development and operations. They manage CI/CD pipelines, cloud infrastructure, containerization and monitoring. Salaries 15-40 LPA.",
                "Learn Docker, Kubernetes, Jenkins and AWS/Azure. Get AWS DevOps Professional certification. Target product companies.",
                listOf("Docker & Kubernetes","CI/CD Jenkins/GitHub Actions","Linux & Bash","AWS/Azure/GCP","Terraform","Monitoring Prometheus/Grafana"),
                listOf("AWS DevOps Professional" to "Amazon","CKA Kubernetes Admin" to "CNCF","HashiCorp Terraform" to "HashiCorp"),
                listOf("CI/CD Pipeline" to "Set up complete Jenkins CI/CD pipeline.","Docker App" to "Containerise web app using Docker Compose.","Kubernetes Cluster" to "Deploy microservice on local Kubernetes cluster.")),

            Bundle(87,"Cloud Architects design cloud computing strategy and infrastructure. Work with AWS, Azure and GCP to build scalable, secure, cost-efficient systems for large organisations.",
                "Get AWS Solutions Architect Professional certification. Gain 3-5 years cloud experience. Target Accenture, Deloitte or cloud providers.",
                listOf("AWS/Azure/GCP","Cloud Architecture Patterns","Networking VPC/DNS/CDN","Security & IAM","Cost Optimization","Disaster Recovery"),
                listOf("AWS Solutions Architect Pro" to "Amazon","Google Cloud Professional Architect" to "Google","Azure Solutions Architect" to "Microsoft"),
                listOf("3-Tier Architecture" to "Deploy 3-tier web app on AWS.","Cost Optimization" to "Analyse and reduce AWS bill by 30%.","DR Plan" to "Design complete disaster recovery plan.")),

            Bundle(88,"Embedded Systems Engineers design software for microcontrollers in products like washing machines, cars, medical devices and satellites. Critical for IoT and automotive industries.",
                "Master C programming and RTOS. Work on Arduino/Raspberry Pi projects. Target automotive companies, ISRO, DRDO.",
                listOf("Embedded C Programming","Microcontrollers Arduino/STM32","RTOS","PCB Design","CAN/SPI/I2C Protocols","FPGA Programming"),
                listOf("ARM Embedded Cert" to "ARM Education","AUTOSAR" to "Vector Informatik","GATE ECE" to "IIT/IISC"),
                listOf("Smart Home" to "Build home automation system using ESP8266.","Digital Clock" to "Build 7-segment digital clock with RTC module.","RTOS Project" to "Implement task scheduling using FreeRTOS.")),

            Bundle(89,"VLSI Design Engineers design microchips and semiconductor circuits. Work at Intel, Qualcomm, Samsung, MediaTek and ISRO. Highest paying hardware engineering field.",
                "Pursue M.Tech in VLSI for best placements. Master Verilog/VHDL. Target semiconductor companies. Excellent overseas opportunities.",
                listOf("Digital Logic Design","Verilog/VHDL","FPGA Design","ASIC Design Flow","Static Timing Analysis","Synopsys/Cadence Tools"),
                listOf("GATE ECE" to "IIT/IISC","Cadence Virtuoso" to "Cadence","Synopsys Training" to "Synopsys"),
                listOf("4-bit ALU" to "Design and simulate 4-bit ALU in Verilog.","UART Controller" to "Implement UART serial communication in VHDL.","FPGA LED Matrix" to "Display patterns on 8x8 LED matrix using FPGA.")),

            Bundle(90,"IoT Engineers design connected device systems combining hardware, firmware, cloud and mobile apps. IoT powers smart homes, smart cities, agriculture sensors and industrial automation.",
                "Master Arduino, Raspberry Pi, MQTT and cloud IoT platforms. Target product companies, smart city projects and industrial automation firms.",
                listOf("Arduino/Raspberry Pi","Sensor Integration","MQTT Protocol","Cloud IoT Platforms","Python/C for Embedded","Data Analytics"),
                listOf("AWS IoT Core" to "Amazon","Azure IoT Developer" to "Microsoft","Cisco IoT Cert" to "Cisco"),
                listOf("Weather Station" to "Build IoT weather monitoring station with dashboard.","Smart Irrigation" to "Automate plant watering using soil moisture sensor.","Asset Tracker" to "Build GPS asset tracking system with Google Maps.")),

            Bundle(91,"AI/ML Engineers build production AI systems at scale. Work on recommendation engines at Netflix, fraud detection at banks and autonomous driving at Tesla. Top paying role globally.",
                "Master MLOps and model deployment. Build end-to-end ML projects. Target FAANG, AI research labs or well-funded AI startups.",
                listOf("Python & PyTorch","MLOps & Model Deployment","Deep Learning Architectures","Feature Engineering","Distributed Training","A/B Testing"),
                listOf("TensorFlow Developer" to "Google","MLOps Specialization" to "DeepLearning.AI","AWS ML Specialty" to "Amazon"),
                listOf("ML API" to "Deploy ML model as REST API with monitoring.","NLP App" to "Build document summarisation tool.","Vision App" to "Build product defect detector for manufacturing.")),

            Bundle(92,"Blockchain Developers build decentralised applications, smart contracts and cryptocurrency systems on Ethereum, Solana and Hyperledger. Booming with DeFi and Web3.",
                "Learn Solidity and Web3.js. Build DeFi or NFT projects. Target fintech startups or CBDC projects with RBI/banks.",
                listOf("Solidity Smart Contracts","Web3.js/Ethers.js","Ethereum/Solana","DeFi Protocols","Cryptography","Node.js Backend"),
                listOf("Ethereum Developer Cert" to "ConsenSys Academy","Hyperledger Fabric" to "Linux Foundation","Solana Developer" to "Solana Foundation"),
                listOf("ERC-20 Token" to "Deploy ERC-20 token on Ethereum testnet.","NFT Platform" to "Build simple NFT minting and trading platform.","DAO Voting" to "Create decentralised voting system using smart contracts.")),

            Bundle(93,"AR/VR Developers create immersive experiences for gaming, education, healthcare training, real estate and military simulation using Unity, Unreal Engine and Meta Quest.",
                "Master Unity and C#. Build AR/VR portfolio. Target gaming companies, healthcare simulation firms or metaverse startups.",
                listOf("Unity 3D/Unreal Engine","C# Programming","3D Modelling Blender","ARKit/ARCore","VR Interaction Design","Spatial Computing"),
                listOf("Unity Certified Developer" to "Unity Technologies","Unreal Engine Cert" to "Epic Games","Meta Spark AR Cert" to "Meta"),
                listOf("VR Museum" to "Create virtual museum tour in Unity VR.","AR Navigation" to "Build AR campus navigation app.","VR Training" to "Develop fire safety training simulation in VR.")),

            Bundle(94,"Quantum Computing researchers work on quantum algorithms and hardware that will solve problems impossible for classical computers. Emerging frontier technology.",
                "Pursue M.Tech/PhD in Quantum Computing. Learn Qiskit from IBM. Target IBM Quantum, Google Quantum AI or IISc research.",
                listOf("Quantum Mechanics Basics","Qiskit/Cirq","Quantum Algorithms Grover/Shor","Linear Algebra","Python Programming","Quantum Error Correction"),
                listOf("IBM Quantum Developer" to "IBM","QC Fundamentals" to "edX/MIT","Google Quantum AI" to "Google Research"),
                listOf("Grover Algorithm" to "Implement Grover search algorithm in Qiskit.","Quantum Circuit" to "Design and simulate 5-qubit quantum circuit.","Research Paper" to "Write survey on quantum cryptography.")),

            Bundle(98,"MCA after BCA gives M.Tech equivalent status in software industry. Opens doors to product companies and higher salary bands. Natural progression from BCA.",
                "Pursue MCA from NIT, BITS or good university. Apply to product companies or pursue PhD in Computer Science.",
                listOf("Advanced Algorithms","System Programming","Software Architecture","Cloud Computing","AI/ML Basics","Project Management"),
                listOf("GATE CS" to "IIT/IISC","AWS Solutions Architect" to "Amazon","PMP" to "PMI"),
                listOf("ERP System" to "Build mini ERP system for small business.","AI Chatbot" to "Build customer service chatbot.","Microservices" to "Build microservices architecture with Docker.")),

            Bundle(99,"Web Developers build websites and web applications. Full stack developers handling React and Node.js are most in demand. Income ranges from 3 LPA to 40+ LPA.",
                "Build 5 complete websites. Freelance on Upwork or join startup. Master React, Node.js and databases.",
                listOf("HTML/CSS/JavaScript","React.js","Node.js/Express","MongoDB/PostgreSQL","REST API Design","Git & Deployment"),
                listOf("Meta Front-End Developer" to "Meta/Coursera","Full Stack Web Dev" to "The Odin Project","AWS Cloud Practitioner" to "Amazon"),
                listOf("Portfolio" to "Build stunning personal portfolio website.","Blog Platform" to "Create full-stack blog with authentication.","E-Commerce" to "Build online store with payment integration.")),

            Bundle(100,"Data Analysts collect, clean, analyse and visualise data to help businesses make decisions. Use Excel, SQL, Python and Power BI/Tableau to create dashboards and reports.",
                "Master SQL and Power BI. Get Google Data Analytics certificate. Target e-commerce, banking, consulting and FMCG companies.",
                listOf("SQL & Excel","Python Pandas","Power BI/Tableau","Statistics","Data Cleaning","Business Intelligence"),
                listOf("Google Data Analytics" to "Google/Coursera","Microsoft Power BI" to "Microsoft","Tableau Desktop Specialist" to "Tableau"),
                listOf("Sales Dashboard" to "Create interactive Power BI sales dashboard.","SQL Analysis" to "Write 50 complex SQL queries on real datasets.","Cohort Analysis" to "Perform customer cohort analysis for e-commerce dataset.")),

            Bundle(101,"Business Intelligence professionals design data warehouses, ETL pipelines and executive dashboards that help organisations track KPIs and make strategic decisions.",
                "Master SQL, Power BI and data warehousing. Get Microsoft BI certification. Target large corporates, consulting firms and banking sector.",
                listOf("Data Warehousing","ETL Processes","Power BI/Tableau","SQL & SSAS","Business Acumen","Dashboard Design"),
                listOf("Microsoft Power BI Analyst" to "Microsoft","Tableau BI Cert" to "Tableau","Snowflake SnowPro" to "Snowflake"),
                listOf("Data Warehouse" to "Design star schema data warehouse for retail.","ETL Pipeline" to "Build ETL pipeline using Python and SQL.","Executive Dashboard" to "Build C-suite KPI dashboard in Power BI.")),

            Bundle(102,"Security Analysts monitor networks for cyber threats, investigate incidents and implement protective measures. Work in SOC of banks, IT firms and government.",
                "Get CompTIA Security+ and SOC Analyst certification. Practice on TryHackMe SOC path. Target BFSI sector and CERT-IN.",
                listOf("SIEM Splunk/QRadar","Log Analysis","Incident Response","Network Security Monitoring","Threat Intelligence","Vulnerability Management"),
                listOf("CompTIA Security+" to "CompTIA","Google Cybersecurity Cert" to "Google/Coursera","Splunk Core Certified" to "Splunk"),
                listOf("SIEM Setup" to "Set up Splunk and monitor logs for anomalies.","Incident Response Plan" to "Write incident response runbook.","Threat Report" to "Analyse a real cyber attack case study.")),

            Bundle(103,"Ethical Hackers legally attack systems to find vulnerabilities before malicious hackers. Write detailed reports and help organisations fix security gaps.",
                "Get CEH and OSCP certification. Practice on HackTheBox and VulnHub. Start bug bounty hunting on HackerOne for income.",
                listOf("Penetration Testing Methodology","Kali Linux","Metasploit","Web App Hacking OWASP","Network Hacking","Report Writing"),
                listOf("CEH" to "EC-Council","OSCP" to "Offensive Security","eJPT" to "eLearnSecurity"),
                listOf("Bug Bounty" to "Submit valid bug report on HackerOne.","Home Lab" to "Set up vulnerable VM lab and exploit it.","Pentest Report" to "Write professional penetration testing report.")),

            Bundle(108,"Investment Bankers work on billion-dollar deals including IPOs, mergers and acquisitions. Work at Goldman Sachs, Morgan Stanley. Extraordinary salaries of 20-100+ LPA.",
                "Crack CAT for IIM. Intern at leading IB firm. CFA alongside MBA dramatically increases salary and credibility.",
                listOf("Financial Modelling DCF/LBO","Valuation Methods","Capital Markets","M&A Process","Excel & PowerPoint","Pitch Book Creation"),
                listOf("CFA Level 1-3" to "CFA Institute","FMVA" to "CFI","Bloomberg Market Concepts" to "Bloomberg"),
                listOf("Pitch Book" to "Create full investment banking pitch book.","M&A Model" to "Build merger model with synergies in Excel.","IPO Analysis" to "Analyse upcoming IPO and value the company.")),

            Bundle(109,"Financial Analysts evaluate investments, analyse company financials and provide buy/sell recommendations to fund managers and institutional investors.",
                "Pursue CFA alongside MBA. Target equity research firms, mutual funds and corporate finance roles. Bloomberg terminal skills essential.",
                listOf("Equity Research","Financial Statement Analysis","DCF Valuation","Industry Analysis","Excel Modelling","Report Writing"),
                listOf("CFA Level 1" to "CFA Institute","FRM Part 1" to "GARP","NISM Certification" to "NISM India"),
                listOf("Stock Research Report" to "Write detailed equity research report on a stock.","Sector Analysis" to "Analyse entire industry sector competitively.","Virtual Portfolio" to "Manage virtual investment portfolio for 6 months.")),

            Bundle(110,"Risk Managers identify, assess and mitigate financial, operational and strategic risks in banks, insurance companies and corporates. FRM is the gold standard certification.",
                "Pursue FRM certification alongside MBA. Target risk departments in SBI, HDFC, insurance companies and consulting firms.",
                listOf("Credit Risk Analysis","Market Risk VaR","Operational Risk","Basel III Compliance","Risk Modelling","Stress Testing"),
                listOf("FRM Part 1 & 2" to "GARP","PRM" to "PRMIA","NISM Risk" to "NISM India"),
                listOf("VaR Model" to "Build Value-at-Risk model in Excel.","Credit Scorecard" to "Create credit scoring model for loan applicants.","Risk Report" to "Write comprehensive enterprise risk management report.")),

            Bundle(111,"Digital Marketers help brands grow online through SEO, social media, paid ads, email marketing and content strategy. Every business is going digital creating massive demand.",
                "Get Google Digital Marketing certification. Build portfolio of campaigns. Target digital agencies, e-commerce companies or go freelance.",
                listOf("SEO & SEM","Google Ads & Meta Ads","Content Marketing","Email Marketing","Social Media Strategy","Analytics GA4"),
                listOf("Google Digital Marketing" to "Google/Coursera","Meta Blueprint Cert" to "Meta","HubSpot Marketing" to "HubSpot"),
                listOf("SEO Audit" to "Perform complete SEO audit of a website.","Ad Campaign" to "Run a 500 rupee Google Ads campaign and track results.","Content Calendar" to "Create 3-month social media content calendar.")),

            Bundle(112,"Brand Managers develop brand identity, positioning and perception for consumer products. Work on packaging, advertising, pricing and customer experience at FMCG companies.",
                "Join FMCG companies like HUL, P&G, Nestle or ITC through MBA marketing. These companies have best brand management training.",
                listOf("Brand Strategy","Consumer Research","Advertising & Media Planning","Product Development","Pricing Strategy","Trade Marketing"),
                listOf("MBA Marketing" to "IIM/XLRI","Google Brand Marketing" to "Google/Coursera","IIM Brand Management" to "IIM Online"),
                listOf("Brand Audit" to "Audit brand identity of a popular consumer brand.","New Product Launch" to "Design complete new product launch plan.","Consumer Survey" to "Conduct consumer research for brand repositioning.")),

            Bundle(113,"Product Managers define product vision, prioritise features, work with engineering and design teams. Called CEO of the product. Salary ranges from 15-60 LPA.",
                "Transition from engineering or design into PM roles. Target tech product companies. Get Google PM certification.",
                listOf("Product Strategy","User Research","Agile & Scrum","Data-Driven Decisions","Stakeholder Management","Roadmap Planning"),
                listOf("Google PM Certificate" to "Google/Coursera","Pragmatic Marketing" to "Pragmatic Institute","Agile Scrum Master" to "Scrum Alliance"),
                listOf("PRD Document" to "Write complete Product Requirements Document.","User Research" to "Conduct 10 user interviews for a product feature.","Product Roadmap" to "Create 6-month product roadmap with prioritization.")),

            Bundle(114,"HR Business Partners align human resources strategy with business goals. Handle talent acquisition, performance management, L&D and employee relations.",
                "Join large corporates or MNCs in HR roles. SHRM or XLRI HR certification adds significant credibility. Target TCS, Infosys, Deloitte HR consulting.",
                listOf("Talent Acquisition","Performance Management","HR Analytics","Employee Relations","Compensation & Benefits","Organizational Development"),
                listOf("SHRM-CP" to "SHRM USA","XLRI HR Diploma" to "XLRI","People Analytics Cert" to "Coursera/Wharton"),
                listOf("HR Dashboard" to "Build Power BI HR analytics dashboard.","Job Descriptions" to "Write JDs for 10 different roles in tech company.","Engagement Survey" to "Design and analyse employee engagement survey.")),

            Bundle(115,"Talent Acquisition specialists find, attract and hire the best candidates. Use LinkedIn Recruiter, job portals, campus hiring and referrals to fill critical positions quickly.",
                "Start in HR generalist role then specialise in recruitment. Get LinkedIn Recruiter certification. Target staffing firms and large corporates.",
                listOf("Sourcing & Headhunting","LinkedIn Recruiter","Interviewing Techniques","Employer Branding","ATS Workday/SuccessFactors","Offer Negotiation"),
                listOf("LinkedIn Recruiter Cert" to "LinkedIn Learning","AIRS Certification" to "ADP","HR Generalist Cert" to "HRCI"),
                listOf("Campus Drive" to "Plan and execute a campus recruitment drive.","Sourcing Project" to "Source 20 qualified candidates for niche role.","JD Optimization" to "Rewrite 5 job descriptions to attract better talent.")),

            Bundle(116,"Supply Chain Analysts optimise flow of goods from raw materials to end customers. Use data analysis, demand forecasting and inventory optimisation to reduce costs.",
                "Get APICS CSCP certification. Target FMCG, e-commerce (Amazon, Flipkart), automobile and logistics companies.",
                listOf("Demand Forecasting","Inventory Management","SAP SCM/Oracle","Logistics & Transportation","Supplier Management","Data Analysis"),
                listOf("APICS CSCP" to "APICS USA","Six Sigma Green Belt" to "ASQ","SAP SCM Cert" to "SAP"),
                listOf("Inventory Model" to "Build EOQ and safety stock model in Excel.","Demand Forecast" to "Forecast demand using time series analysis.","Supplier Scorecard" to "Create vendor performance evaluation scorecard.")),

            Bundle(117,"Logistics Managers coordinate transportation, warehousing and delivery of goods. Manage fleets, 3PL partners, customs clearance and last-mile delivery operations.",
                "Target Delhivery, Blue Dart, FedEx, Amazon, Flipkart or FMCG companies. MBA in Operations adds significant value.",
                listOf("Transportation Management","Warehouse Operations","Customs & Import/Export","3PL Management","Route Optimization","ERP Systems"),
                listOf("CLTD" to "APICS USA","FIATA Diploma" to "FIATA","Supply Chain Cert" to "Coursera/Rutgers"),
                listOf("Route Optimization" to "Optimise delivery routes for a city using algorithms.","Warehouse Layout" to "Design efficient warehouse layout plan.","Import Docs" to "Prepare complete import shipment document set.")),

            Bundle(121,"Primary School Teachers teach children aged 6-12 years in Maths, Science, Languages and Arts. They shape the foundation of a child's education and character.",
                "After B.Ed clear CTET or State TET exam for government school jobs. Private schools hire directly. Salary 3-8 LPA in private schools.",
                listOf("Child Psychology","Lesson Planning","Activity-Based Learning","Classroom Management","Assessment & Evaluation","Digital Teaching Tools"),
                listOf("CTET" to "CBSE India","State TET" to "State Education Board","Early Childhood Diploma" to "IGNOU"),
                listOf("Lesson Plans" to "Create 10 detailed lesson plans for different subjects.","Teaching Aid" to "Create innovative teaching aids using local materials.","Assessment Tool" to "Design formative assessment tool for primary students.")),

            Bundle(122,"School Principals lead entire schools managing teachers, curriculum, administration, student welfare and parent relations. Visionary leaders of educational institutions.",
                "Gain 10+ years teaching experience then pursue Diploma in Educational Administration. Target principal positions through school management trust interviews.",
                listOf("Educational Leadership","School Administration","Staff Management","Curriculum Development","Student Welfare","Budgeting & Finance"),
                listOf("Education Admin Diploma" to "IGNOU","School Leadership Cert" to "British Council","MBA Education Mgmt" to "University"),
                listOf("School Plan" to "Write 3-year school improvement plan.","Teacher Appraisal" to "Design teacher performance evaluation system.","Parent Program" to "Create parent engagement program for school.")),

            Bundle(123,"High School Teachers teach students aged 13-18 in specialised subjects like Physics, Chemistry, Maths, History or English. Guide students for board exams and career choices.",
                "Clear CTET Paper 2 or State TET for secondary level. Navodaya, KVS and NVS recruitment offers excellent government school jobs.",
                listOf("Subject Expertise","Lesson Design","Board Exam Preparation","Student Counselling","ICT in Education","Classroom Management"),
                listOf("CTET Paper 2" to "CBSE India","KVS Teacher Exam" to "KVS India","UGC NET Education" to "NTA India"),
                listOf("Revision Material" to "Create comprehensive revision notes for board exam subject.","Practice Tests" to "Design 5 chapter-wise test papers with solutions.","Career Guidance" to "Conduct career guidance workshop for Class 10 students.")),

            Bundle(124,"College Lecturers teach undergraduate and postgraduate students in universities. They conduct research, publish papers and guide student projects.",
                "After M.Ed or M.Sc crack UGC NET for Assistant Professor eligibility. PhD required for tenure-track positions.",
                listOf("Subject Expertise (Deep)","Research & Publication","Academic Writing","Curriculum Design","Project Guidance","Grant Writing"),
                listOf("UGC NET" to "NTA India","PhD Research" to "University","Faculty Development" to "HRDC/UGC"),
                listOf("Research Paper" to "Publish paper in UGC-listed journal.","Syllabus Design" to "Design semester syllabus for your subject.","National Seminar" to "Organise national-level academic seminar.")),

            Bundle(125,"Special Needs Educators work with children having learning disabilities, autism, hearing/visual impairment using adapted teaching methods. Growing career with social impact.",
                "Register with RCI (Rehabilitation Council of India). Work in special schools, NGOs or inclusive education programs.",
                listOf("Special Education Methods","Individualized Education Plans IEP","Behaviour Management","Assistive Technology","Sign Language Basics","Sensory Integration"),
                listOf("RCI Registration" to "RCI India","Special Education Diploma" to "NIMH/University","Montessori Cert" to "AMI"),
                listOf("IEP Development" to "Create Individualized Education Plan for a child.","Adaptive Material" to "Design adapted learning materials for dyslexic students.","Parent Workshop" to "Conduct workshop for parents of special needs children."))
        )

        for (b in bundles) {
            dao.insertDetail(CareerDetailEntity(nodeId = b.nodeId, description = b.desc, suggestedNextStep = b.next))
            dao.insertSkills(b.skills.map { SkillEntity(nodeId = b.nodeId, skillName = it) })
            dao.insertCertifications(b.certs.map { (n, p) -> CertificationEntity(nodeId = b.nodeId, certName = n, provider = p) })
            dao.insertProjects(b.projects.map { (n, d) -> ProjectEntity(nodeId = b.nodeId, projectName = n, description = d) })
        }
    }
    private suspend fun seedPhaseADetails(dao: CareerDetailDao) {
        data class Bundle(
            val nodeId: Int, val desc: String, val next: String,
            val skills: List<String>,
            val certs: List<Pair<String, String>>,
            val projects: List<Pair<String, String>>
        )
        val bundles = listOf(
            // GOVERNMENT JOBS
            Bundle(150,"IAS officers are the backbone of India's civil administration. As District Collector or DM, you oversee law & order, revenue, development and welfare of millions of citizens. Highest prestige government career.",
                "Clear UPSC CSE (Prelims → Mains → Interview). Minimum 3-4 years of dedicated preparation. Start with NCERT books then standard references like Laxmikanth, Bipin Chandra.",
                listOf("General Studies (History/Geography/Polity)","Current Affairs & Editorial Analysis","Essay Writing","Ethics & Integrity","Decision Making","Public Administration"),
                listOf("UPSC CSE" to "UPSC India","LBSNAA Training" to "Govt of India","State PCS" to "State PSC"),
                listOf("Daily Editorial Notes" to "Read and summarise 2 editorials daily for 1 year.","Mock Interviews" to "Attend 10 mock interview sessions with experts.","Answer Writing" to "Practice 5 UPSC mains answer writings daily.")),

            Bundle(151,"IPS officers lead the police force at district, state and national level. They fight crime, maintain law & order, lead anti-terrorism operations and protect national security.",
                "Clear UPSC CSE with high rank (typically top 200). Physical fitness is essential. Undergo training at Sardar Vallabhbhai Patel NPA, Hyderabad.",
                listOf("Criminal Law & Procedure","Intelligence Gathering","Physical Fitness","Crisis Management","Public Relations","Forensic Investigation Basics"),
                listOf("UPSC CSE" to "UPSC India","NPA Training" to "SVP NPA Hyderabad","State Police Exam" to "State PSC"),
                listOf("Legal Case Study" to "Analyse 10 landmark criminal law cases in India.","Current Crime Report" to "Write monthly report on crime trends in your district.","Physical Training" to "Maintain fitness log meeting IPS physical standards.")),

            Bundle(152,"IFS officers represent India abroad as diplomats, ambassadors and consular officers. They negotiate treaties, protect Indian citizens abroad and promote India's foreign policy interests.",
                "Clear UPSC CSE with very high marks (typically top 50). Learn at least one foreign language. Join FSOI training at Sushma Swaraj Institute of Foreign Service.",
                listOf("International Relations","Diplomatic Protocol","Foreign Languages","Economic Diplomacy","Public Speaking","Crisis Negotiation"),
                listOf("UPSC CSE" to "UPSC India","SSIFS Training" to "MEA India","UN Language Exam" to "United Nations"),
                listOf("Country Report" to "Write detailed geopolitical analysis of a country.","Debate" to "Participate in MUN (Model United Nations) competition.","Language Cert" to "Achieve B2 level in a UN official language.")),

            Bundle(153,"IRS officers manage India's tax system including Income Tax and Customs & Excise. They investigate tax evasion, conduct raids and collect revenue for the government.",
                "Clear UPSC CSE. Posted under CBDT (Income Tax) or CBIC (Customs & GST). Strong accounting and law knowledge is very useful.",
                listOf("Income Tax Law","GST & Customs","Financial Investigation","Accounting","Legal Drafting","Data Analysis"),
                listOf("UPSC CSE" to "UPSC India","NADT Training" to "National Academy Direct Taxes","NACIN Training" to "Customs & Indirect Taxes"),
                listOf("Tax Case Study" to "Analyse 5 famous tax evasion cases in India.","IT Return Analysis" to "Study complex income tax return filing scenarios.","GST Study" to "Create comprehensive notes on GST law and rules.")),

            Bundle(154,"IES (Indian Engineering Service) officers work as engineers in government organisations like Railways, PWD, CPWD, telecom and defence production. Highly respected technical government career.",
                "Clear UPSC ESE (Engineering Services Exam) after B.Tech. Branch-specific technical knowledge is key. Prepare for both technical and GS papers.",
                listOf("Branch-Specific Engineering","Engineering Mathematics","General Studies","Technical Drawing & Design","Project Management","Government Procurement Rules"),
                listOf("UPSC ESE" to "UPSC India","GATE" to "IIT/IISC","State Engineering Services" to "State PSC"),
                listOf("Technical Study" to "Master all previous 10 years UPSC ESE papers.","Engineering Project" to "Build a practical project in your engineering branch.","GS Notes" to "Create comprehensive General Studies notes for ESE.")),

            Bundle(155,"Bank PO (Probationary Officer) is one of the most sought-after government jobs. Bank POs manage branches, sanction loans, handle customer relations and rise to become bank managers.",
                "Clear IBPS PO or SBI PO exam (Prelims → Mains → Interview). Practice aptitude, reasoning and English daily. Attempt multiple bank exams simultaneously.",
                listOf("Quantitative Aptitude","Logical Reasoning","English Language","Banking & Financial Awareness","Computer Basics","Current Affairs"),
                listOf("IBPS PO" to "IBPS India","SBI PO" to "SBI India","RBI Assistant" to "RBI India"),
                listOf("Mock Tests" to "Solve 50 full-length bank exam mock tests.","Current Affairs Log" to "Maintain daily banking news notes for 6 months.","Speed Maths" to "Practice 100 aptitude questions daily for accuracy and speed.")),

            Bundle(156,"Bank Clerk is an entry-level government banking job with excellent job security, salary and benefits. Bank clerks handle customer transactions, account opening and basic banking services.",
                "Clear IBPS Clerk or SBI Clerk exam. Easier than PO exam but still competitive. Good career starting point with promotion possibilities to officer grade.",
                listOf("Basic Mathematics","Reasoning Ability","English Comprehension","Computer Knowledge","Banking Basics","Customer Service"),
                listOf("IBPS Clerk" to "IBPS India","SBI Clerk" to "SBI India","IPPB Clerk" to "India Post Payments Bank"),
                listOf("Mock Tests" to "Solve 30 IBPS Clerk mock tests in timed conditions.","Typing Practice" to "Achieve 30 WPM typing speed with accuracy.","Banking Notes" to "Study all banking products and services thoroughly.")),

            Bundle(157,"RBI Grade B Officer is one of India's most prestigious banking jobs. RBI officers formulate monetary policy, regulate banks, manage foreign exchange and conduct economic research.",
                "Clear RBI Grade B exam (Phase 1 → Phase 2 → Interview). Phase 2 has Economics, Finance and Management papers. Very competitive with excellent perks.",
                listOf("Macroeconomics & Monetary Policy","Financial Markets","Banking Regulation","Research & Report Writing","Quantitative Methods","Current Economic Affairs"),
                listOf("RBI Grade B" to "RBI India","SEBI Grade A" to "SEBI India","NABARD Grade A" to "NABARD India"),
                listOf("Economic Analysis" to "Write monthly economic analysis of RBI policy decisions.","Research Paper" to "Write a research paper on Indian monetary policy.","Mock Interview" to "Attend RBI Grade B mock interview with finance experts.")),

            Bundle(159,"RRB NTPC (Non-Technical Popular Category) covers clerical and commercial roles in Indian Railways such as Junior Clerk, Goods Guard, Station Master Assistant and Traffic Assistant.",
                "Clear RRB NTPC exam (CBT 1 → CBT 2 → Typing/Skills Test). One of India's most popular exams with lakhs of applicants. Good government job with railway benefits.",
                listOf("General Awareness","Mathematics","General Intelligence & Reasoning","English","Computer Basics","Railway Knowledge"),
                listOf("RRB NTPC" to "Railway Recruitment Board","RRB Group D" to "Indian Railways","RRC Apprentice" to "Zonal Railways"),
                listOf("Mock Tests" to "Solve 40 RRB NTPC mock tests in timed conditions.","GK Notes" to "Create topic-wise general knowledge notes for railway exams.","Reasoning Practice" to "Solve 50 reasoning puzzles daily for 3 months.")),

            Bundle(161,"Railway Loco Pilot (Assistant Loco Pilot/ALP) operates railway locomotives. One of the most responsible jobs in Indian Railways with good salary and job security.",
                "Clear RRB ALP exam (CBT 1 → CBT 2 → Computer-Based Aptitude Test). Technical diploma or ITI is required for ALP eligibility.",
                listOf("Railway Operations & Signalling","Basic Electrical & Mechanical","Physics & Mathematics","General Intelligence","Railway Rules & Acts","Safety Procedures"),
                listOf("RRB ALP" to "Railway Recruitment Board","RRB Technician" to "Indian Railways","RDSO Training" to "Research Designs & Standards Org"),
                listOf("Technical Study" to "Master all technical trade subjects for ALP exam.","Signal System Study" to "Learn complete railway signalling and safety system.","Mock Tests" to "Solve 30 RRB ALP mock tests focusing on technical section.")),

            Bundle(163,"SSC CGL (Combined Graduate Level) is India's most popular exam for central government jobs. Covers posts like Income Tax Inspector, CBI Sub-Inspector, Assistant Audit Officer and many more.",
                "Clear SSC CGL (Tier 1 → Tier 2 → Tier 3 → Tier 4). Prepare all four sections equally. Very competitive with 30+ lakh applicants for thousands of posts.",
                listOf("Quantitative Aptitude","English Language & Comprehension","General Awareness","Reasoning Ability","Computer Proficiency","Current Affairs"),
                listOf("SSC CGL" to "SSC India","SSC CHSL" to "SSC India","SSC MTS" to "SSC India"),
                listOf("Tier 1 Mock Tests" to "Solve 60 SSC CGL Tier 1 mock tests.","Previous Papers" to "Solve all SSC CGL papers from last 10 years.","Vocab Building" to "Learn 20 new English words daily for 6 months.")),

            Bundle(170,"ISRO Scientists work on India's space missions including Chandrayaan, Mangalyaan and Gaganyaan. They design rockets, satellites, propulsion systems and conduct cutting-edge space research.",
                "Get top rank in GATE CS/ECE/ME/AE. ISRO recruits through ICRB exam and campus placements from IITs/NITs. M.Tech in relevant field is highly preferred.",
                listOf("Aerospace/Electronics/Computer Engineering","MATLAB & Simulation Tools","Orbital Mechanics","Embedded Systems","C & Python Programming","Research & Documentation"),
                listOf("GATE" to "IIT/IISC","ICRB Scientist" to "ISRO India","M.Tech Admission" to "ISRO IIST University"),
                listOf("Satellite Model" to "Design a CubeSat model with basic telemetry.","Orbit Simulation" to "Simulate satellite orbits using MATLAB/Python.","Research Paper" to "Publish paper on aerospace or electronics at national conference.")),

            Bundle(171,"DRDO Scientists develop defence equipment including missiles, radar systems, armoured vehicles, sonar and electronic warfare systems to make India self-reliant in defence technology.",
                "Qualify GATE with high score. DRDO recruits Scientist B through SET exam. M.Tech from IIT/NIT significantly improves chances of selection.",
                listOf("Defence Technology Basics","Research Methodology","Electronics/Mechanical/CS Engineering","Technical Writing","Project Management","Testing & Quality Assurance"),
                listOf("GATE" to "IIT/IISC","DRDO SET Exam" to "DRDO India","M.Tech Admission" to "DIAT Pune / IIT"),
                listOf("Mini Project" to "Build a working prototype of a defence-relevant system.","Research Paper" to "Present at national defence technology conference.","Technical Notes" to "Study all DRDO annual technology development reports.")),

            // DEFENCE MILITARY
            Bundle(184,"Indian Army Officers lead soldiers in combat operations, anti-terrorism, disaster relief and peacekeeping missions. One of the most respected and honourable careers in India.",
                "Clear NDA exam after 12th or CDS after graduation. Go through SSB (Service Selection Board) interview. Training at IMA Dehradun lasts 18 months.",
                listOf("Physical Fitness & Endurance","Leadership & Decision Making","Tactical & Strategic Thinking","Weapon Handling & Drill","Military Law & Ethics","Map Reading & Navigation"),
                listOf("NDA Exam" to "UPSC India","CDS Exam" to "UPSC India","TES / TGC Entry" to "Indian Army"),
                listOf("Physical Training" to "Run 5km under 25 minutes and do 40 pushups daily.","SSB Preparation" to "Practice GTO tasks, psychology tests and personal interview.","Current Affairs" to "Study defence and international affairs daily for SSB.")),

            Bundle(185,"Army Soldier (General Duty) is an entry-level career in the Indian Army. GD soldiers serve in infantry, artillery, armoured corps and are the backbone of India's land forces.",
                "Clear Army Agniveer or Soldier GD recruitment rallies. Physical fitness is paramount. Age: 17.5-23 years. Height and weight criteria must be met.",
                listOf("Physical Fitness (Running/Pushups/Pullups)","Basic Weapons Training","Drill & Discipline","Map Reading","First Aid","Military Customs & Traditions"),
                listOf("Army GD Recruitment Rally" to "Indian Army","Agniveer Scheme" to "Ministry of Defence","Army Technical" to "Indian Army"),
                listOf("Physical Training Log" to "Maintain daily physical training log meeting Army standards.","Academic Prep" to "Study 10th level Maths, Science and GK for Army exam.","Medical Fitness" to "Get complete medical checkup and achieve Army fitness standards.")),

            Bundle(189,"Indian Air Force Officers fly fighter jets, transport aircraft and helicopters while also serving in technical, administrative, logistics and meteorology branches.",
                "Clear NDA or AFCAT exam. Flying branch requires eyesight 6/6. Ground Duty branches open for graduates. Training at AFA Dundigal, Hyderabad.",
                listOf("Physics & Mathematics","Aeronautical Knowledge","Situational Awareness","Physical Fitness","Aviation English","Leadership"),
                listOf("NDA Exam" to "UPSC India","AFCAT" to "Indian Air Force","CDS Air Force Entry" to "UPSC India"),
                listOf("Physics Study" to "Master all aerodynamics and aviation physics concepts.","AFCAT Mock Tests" to "Solve 25 full-length AFCAT mock tests.","Physical Fitness" to "Meet IAF physical standards: 1.6km run in 8 minutes.")),

            Bundle(191,"CRPF and BSF are India's largest central paramilitary forces. Officers lead battalions in internal security operations, anti-Naxal operations, border security and disaster response.",
                "Clear CAPF AC exam conducted by UPSC. Physical fitness and medical standards are strict. Training at respective force academies for 6 months.",
                listOf("Physical Fitness","Leadership & Tactical Skills","Weapon Handling","Law & Constitution","Intelligence Gathering","Crisis Management"),
                listOf("UPSC CAPF AC" to "UPSC India","SSB Interview" to "Service Selection Board","Force-Specific Training" to "CRPF/BSF Academy"),
                listOf("Physical Training" to "Achieve CAPF physical standards in running and field events.","Legal Study" to "Study CrPC, IPC and Constitution for CAPF exam.","SSB Prep" to "Practice SSB tasks including group discussions and lecturette.")),

            // SPORTS
            Bundle(204,"Professional Cricket involves playing for state Ranji Trophy teams, IPL franchises and ultimately the Indian national team. Cricket is India's most popular sport with huge earning potential.",
                "Join a cricket academy at age 8-12. Play for school, district, state age-group teams. Clear state trials to enter Ranji Trophy. Excellence leads to IPL auction and national selection.",
                listOf("Batting/Bowling/Fielding Techniques","Physical Fitness & Agility","Mental Toughness","Match Reading & Strategy","Video Analysis","Cricket Laws & Ethics"),
                listOf("BCCI Level 1 Coach Cert" to "BCCI India","NCA Training" to "NCA Bengaluru","State Cricket Association" to "State Cricket Board"),
                listOf("Net Practice Log" to "Maintain daily net practice log with statistics.","Match Analysis" to "Analyse your own batting/bowling videos and improve.","Fitness Program" to "Follow a cricket-specific strength and conditioning program.")),

            Bundle(206,"Professional Badminton requires training from childhood to compete at national and international level. India has produced world champions like PV Sindhu, Saina Nehwal and Kidambi Srikanth.",
                "Join SAI or state badminton academy. Compete in sub-junior, junior and senior national championships. Ranking points lead to international BWF circuit entries.",
                listOf("Technical Strokes (Smash/Drop/Net)","Footwork & Agility","Fitness & Endurance","Match Tactics","Mental Concentration","Injury Prevention"),
                listOf("BWF Certification" to "Badminton World Federation","BAI Ranking" to "Badminton Association India","SAI Training" to "Sports Authority of India"),
                listOf("Training Log" to "Maintain 6-month training diary with performance metrics.","Video Analysis" to "Record and analyse your match play against top players.","Tournament Circuit" to "Participate in 10 state-level tournaments and track ranking.")),

            Bundle(212,"Personal Trainers design customised fitness programs for clients in gyms, homes and online. India's fitness industry is booming with huge demand for qualified trainers.",
                "Get CPT (Certified Personal Trainer) certification from ACSM, NASM or ACE. Work in premium gyms like Gold's Gym, Cult.fit or start your own online training business.",
                listOf("Anatomy & Physiology","Exercise Science","Nutrition Basics","Program Design","Client Motivation","Injury Prevention & First Aid"),
                listOf("NASM CPT" to "NASM USA","ACSM Certification" to "ACSM USA","NSCA CSCS" to "NSCA USA"),
                listOf("Client Program" to "Design complete 12-week fitness program for a beginner client.","Fitness Assessment" to "Conduct comprehensive fitness assessment and body composition test.","Online Content" to "Create 30 fitness education posts for social media.")),

            Bundle(213,"Yoga Instructors teach yoga asanas, pranayama and meditation to students in yoga studios, gyms, schools, corporate offices and online. Growing internationally due to wellness trend.",
                "Complete 200-hour Yoga Teacher Training (YTT) certified by Yoga Alliance. Register as RYT-200. Target premium yoga studios or start your own online yoga classes.",
                listOf("Yoga Asanas & Alignment","Pranayama & Meditation","Anatomy for Yoga","Teaching Methodology","Sanskrit & Yoga Philosophy","Sequencing & Class Design"),
                listOf("Yoga Alliance RYT-200" to "Yoga Alliance USA","RYT-500 Advanced" to "Yoga Alliance USA","Ayurveda + Yoga" to "Naturopathy Boards"),
                listOf("Teaching Practice" to "Teach 50 free yoga classes to friends and family.","Online Class" to "Launch a YouTube yoga channel with 20 videos.","Workshop" to "Organise a weekend yoga wellness workshop.")),

            Bundle(214,"Sports Coaches train athletes at school, club, state and national level. A good coach can transform an ordinary player into a champion. Huge demand across all sports in India.",
                "Get NIS Coaching Diploma from SAI's National Institute of Sports, Patiala. Obtain sport-specific Level 1, 2, 3 coaching certificates from national federation.",
                listOf("Sport-Specific Technical Knowledge","Training Program Design","Sports Psychology","Injury Management","Video Analysis","Team Management"),
                listOf("NIS Coaching Diploma" to "SAI Patiala","National Federation Cert" to "Sports Federation India","Level 1 Coaching" to "International Federation"),
                listOf("Training Plan" to "Design a 3-month training plan for a junior athlete.","Video Analysis" to "Analyse and improve a player's technique using video.","Tournament Prep" to "Prepare a team for a national-level competition.")),

            // ENTREPRENEURSHIP
            Bundle(225,"App/SaaS Founders build software products used by thousands or millions of users and generate recurring subscription revenue. India's startup ecosystem is one of the world's most vibrant.",
                "Identify a problem, validate with 10 potential users, build MVP. Apply to Y Combinator, Sequoia Surge, or Indian VCs. Register your company under Startup India scheme.",
                listOf("Product Management","Full Stack Development","UI/UX Design","Business Model Design","Fundraising & Pitching","Growth Hacking"),
                listOf("Startup India Registration" to "DPIIT India","Y Combinator Application" to "Y Combinator","Google for Startups" to "Google India"),
                listOf("MVP Launch" to "Build and launch minimum viable product in 30 days.","User Interviews" to "Conduct 20 customer discovery interviews.","Pitch Deck" to "Create investor pitch deck and present to 5 angel investors.")),

            Bundle(228,"NGO/Non-Profit founders address social problems in education, healthcare, environment, women empowerment and rural development. Highly impactful career with personal satisfaction.",
                "Register your NGO under Societies Registration Act or Section 8 Company. Apply for CSR funding from corporates and government grants under NITI Aayog Darpan.",
                listOf("Social Problem Analysis","Community Mobilisation","Grant Writing & Fundraising","Project Management","Impact Measurement","Government Relations"),
                listOf("NGO Registration" to "Registrar of Societies","FCRA Registration" to "MHA India","12A/80G Tax Cert" to "Income Tax Dept"),
                listOf("Community Survey" to "Conduct detailed survey of social problem in your community.","Grant Proposal" to "Write a compelling grant proposal for government funding.","Impact Report" to "Create annual impact report showing outcomes achieved.")),

            Bundle(231,"Retail/E-Commerce entrepreneurs sell products online through their own website, Amazon, Flipkart, Meesho or social commerce. Low investment to start, huge scale possible.",
                "Start on Amazon or Flipkart as seller. Build your own Shopify store. Target a niche product category. Focus on quality, pricing and customer reviews for growth.",
                listOf("Product Sourcing & Procurement","E-Commerce Platform Management","Digital Marketing","Inventory Management","Customer Service","Logistics & Shipping"),
                listOf("Flipkart Seller Training" to "Flipkart India","Amazon FBA Course" to "Amazon India","Shopify Certification" to "Shopify Partners"),
                listOf("Online Store" to "Launch an online store on Amazon or Meesho with 20 products.","Product Research" to "Research and identify 5 profitable product niches.","Review Campaign" to "Get first 50 reviews for your product through genuine buyers.")),

            Bundle(233,"YouTubers and Content Creators build audiences on YouTube, Instagram, LinkedIn and podcasts. Top Indian creators earn crores per year through ads, brand deals and products.",
                "Pick a niche you are passionate and knowledgeable about. Post consistently for 6-12 months. Monetise through YouTube Partner Program, brand collaborations and digital products.",
                listOf("Video Production & Editing","Scriptwriting & Storytelling","SEO & Thumbnail Design","Social Media Strategy","Public Speaking","Analytics & Monetisation"),
                listOf("YouTube Partner Program" to "YouTube/Google","Meta Content Creator" to "Meta","Google Analytics Cert" to "Google"),
                listOf("YouTube Channel" to "Upload 30 videos in your niche in 90 days.","Analytics Study" to "Analyse top 10 creators in your niche and find patterns.","Brand Pitch" to "Create a media kit and pitch to 5 relevant brands.")),

            Bundle(234,"Photographers capture images for weddings, fashion, products, journalism, wildlife and fine art. India's wedding photography market alone is worth thousands of crores annually.",
                "Master your DSLR or mirrorless camera. Build portfolio of 100 best photos. Assist established photographers. Build Instagram presence. Start with wedding photography for income.",
                listOf("Camera Settings & Exposure","Composition & Lighting","Photo Editing (Lightroom/Photoshop)","Client Communication","Business & Invoicing","Portfolio Building"),
                listOf("NID Photography Course" to "NID Ahmedabad","Lightroom Cert" to "Adobe","Wedding Photographer Assoc" to "WPPI"),
                listOf("Photo Portfolio" to "Build portfolio of 100 best photos across different genres.","Wedding Assist" to "Assist 5 professional photographers at weddings.","Instagram" to "Build Instagram photography account to 1000 followers.")),

            Bundle(236,"Freelance Developers work independently for multiple clients building websites, apps, APIs and software. Can earn 3-30 LPA working from home on their own schedule.",
                "Build a strong GitHub and portfolio. Join Upwork, Toptal, or Fiverr. Start with small projects, build reviews, then raise rates. Focus on one tech stack first.",
                listOf("Full Stack Web Development","Mobile App Development","API Integration","Client Communication","Project Estimation","Time & Task Management"),
                listOf("Upwork Top Rated" to "Upwork","Toptal Certification" to "Toptal","AWS Certification" to "Amazon"),
                listOf("Upwork Profile" to "Create optimised Upwork profile and win first 3 projects.","Portfolio Website" to "Build portfolio showcasing 5 complete projects.","Client Project" to "Deliver a complete web app for a local business client.")),

            Bundle(237,"Freelance Designers create logos, brand identities, UI/UX designs, social media graphics and marketing materials for clients worldwide. Can earn very well working remotely.",
                "Build a strong Behance and Dribbble portfolio. Join 99designs, Fiverr, or direct client outreach. Specialise in branding or UI/UX for higher rates.",
                listOf("Graphic Design (Figma/Illustrator/Photoshop)","Brand Identity Design","UI/UX Design","Typography","Client Briefs & Presentations","Design Project Management"),
                listOf("Adobe Certified Expert" to "Adobe","Google UX Design Cert" to "Google/Coursera","Fiverr Pro" to "Fiverr"),
                listOf("Behance Portfolio" to "Create Behance portfolio with 10 complete design projects.","Brand Identity" to "Design complete brand identity for 3 local businesses.","Dribbble" to "Post 20 high-quality design shots on Dribbble.")),

            // CREATIVE ARTS
            Bundle(254,"Graphic Designers create visual content for brands, marketing, packaging, websites and social media. Every business needs graphic design making this a versatile and in-demand career.",
                "Master Adobe Illustrator and Photoshop. Build portfolio on Behance. Start freelancing or join a design agency, advertising firm or in-house design team.",
                listOf("Adobe Illustrator & Photoshop","Typography & Layout","Colour Theory","Brand Identity Design","Print & Digital Design","Client Communication"),
                listOf("Adobe Certified Expert" to "Adobe","Canva Certified Creator" to "Canva","Coursera Graphic Design" to "Coursera/CalArts"),
                listOf("Logo Design" to "Design logos for 10 different types of businesses.","Brand Kit" to "Create complete brand identity with logo, colours and typography.","Social Media Set" to "Design a complete 30-post social media template pack.")),

            Bundle(256,"Fashion Designers create clothing, accessories and footwear for retail brands, haute couture, films and personal clients. India's fashion industry is worth billions with global brands.",
                "Join NIFT, NID or Pearl Academy for Fashion Design degree. Build portfolio, intern with designers like Manish Malhotra or Ritu Kumar. Start your own label eventually.",
                listOf("Fashion Illustration","Pattern Making & Draping","Textile & Fabric Knowledge","Garment Construction","Trend Forecasting","Fashion Business & Merchandising"),
                listOf("NIFT Entrance" to "NIFT India","NID Entrance" to "NID Ahmedabad","Fashion Design Degree" to "Pearl Academy"),
                listOf("Collection Design" to "Design and stitch a 5-piece mini fashion collection.","Trend Report" to "Create a seasonal fashion trend forecasting report.","Fashion Show" to "Organise a college fashion show with student-designed pieces.")),

            Bundle(257,"Interior Designers transform living spaces, offices, hotels and retail stores into beautiful and functional environments. India's real estate boom is creating enormous demand for designers.",
                "Pursue B.Des or BFA in Interior Design from NID or CEPT. Build portfolio with 3D renders. Work with architecture firms or start your own design studio.",
                listOf("AutoCAD & SketchUp","3D Rendering (3ds Max/Revit)","Space Planning","Material & Colour Selection","Client Consultation","Project Management"),
                listOf("NID Interior Design" to "NID Ahmedabad","CEPT Interior Design" to "CEPT University","AutoCAD Certified" to "Autodesk"),
                listOf("Room Redesign" to "Redesign a room in your home with 3D renders.","Mood Board" to "Create 5 interior design mood boards for different styles.","Client Project" to "Design a complete interior plan for a friend or family member.")),

            Bundle(258,"Actors work in films, TV shows, OTT series, web series, theatre and advertisements. Bollywood, regional cinema and OTT platforms like Netflix India are creating massive opportunities.",
                "Join acting classes at Barry John Acting Studio, Film and Television Institute of India (FTII) or Anupam Kher's Actor Prepares. Build your showreel. Network at film industry events.",
                listOf("Acting Techniques (Method/Stanislavski)","Voice & Diction","Body Language & Movement","Script Reading & Analysis","Camera Presence","Emotional Depth"),
                listOf("FTII Diploma" to "FTII Pune","NSD Acting Degree" to "NSD Delhi","Workshop Cert" to "Barry John / Actor Prepares"),
                listOf("Short Film" to "Act in a short film and build your showreel.","Theatre Play" to "Perform in 5 theatre productions in your city.","Monologue" to "Master 10 monologues from different genres and characters.")),

            Bundle(262,"Musicians and Singers perform in concerts, record albums, compose film music, teach music and perform at weddings and events. India's music industry is expanding with streaming platforms.",
                "Join a music academy from childhood. Learn classical foundation before specialising. Build SoundCloud/YouTube presence. Network at music industry events in Mumbai or Chennai.",
                listOf("Vocal Training / Instrument Mastery","Music Theory & Notation","Recording & Production Basics","Stage Performance","Music Composition","Music Business"),
                listOf("Trinity College Music Grades" to "Trinity College London","Hindustani/Carnatic Classical" to "AICTE Approved Music College","AR Rahman Music School" to "KM Music Conservatory"),
                listOf("Original Song" to "Compose and record 3 original songs professionally.","Live Performance" to "Perform at 10 public events or concerts.","YouTube Channel" to "Upload 20 music cover videos and build audience.")),

            Bundle(265,"Authors and Novelists write books that entertain, educate and inspire millions of readers. Indian publishing is growing with English and regional language authors getting global recognition.",
                "Write your first book in 90 days. Self-publish on Amazon Kindle Direct Publishing or submit to traditional publishers like Penguin, HarperCollins India, or Westland.",
                listOf("Creative Writing & Storytelling","Character Development","Plot Structure & Pacing","Research & World Building","Editing & Proofreading","Publishing & Marketing"),
                listOf("Creative Writing MFA" to "University","Amazon KDP Publishing" to "Amazon","Jaipur Literature Festival" to "HarperCollins"),
                listOf("First Draft" to "Write a complete 50,000-word first draft in 90 days.","Short Stories" to "Write and publish 10 short stories on platforms like Juggernaut.","Writing Blog" to "Maintain a weekly writing blog for 1 year.")),

            Bundle(266,"Content Writers create blog posts, website copy, social media content, whitepapers, email newsletters and scripts for businesses. Remote and freelance opportunities are abundant.",
                "Build a writing portfolio with 20 sample articles in your niche. Join Internshala or Upwork for first projects. Specialise in tech, finance, health or marketing writing for higher pay.",
                listOf("SEO Writing","Research & Fact Checking","Different Content Formats","Grammar & Style","Headline Writing","Content Strategy"),
                listOf("HubSpot Content Marketing" to "HubSpot","Google Digital Marketing" to "Google/Coursera","Copyblogger Certification" to "Copyblogger"),
                listOf("Writing Portfolio" to "Create 20 high-quality writing samples in your niche.","SEO Article" to "Write 5 SEO-optimised articles that rank in Google search.","Client Work" to "Complete content projects for 3 clients on Upwork.")),

            // HEALTHCARE ALLIED
            Bundle(274,"B.Sc Nursing is a 4-year degree that trains professional nurses for hospitals, clinics and community health. Nurses are in extremely high demand both in India and internationally (USA, UK, Canada, Gulf).",
                "Complete B.Sc Nursing then register with State Nursing Council. Work in hospitals like Apollo, Fortis, AIIMS. Pursue M.Sc Nursing for specialisation or move abroad for higher salaries.",
                listOf("Anatomy & Physiology","Nursing Care Procedures","Pharmacology","Medical-Surgical Nursing","Community Health Nursing","Patient Education"),
                listOf("Indian Nursing Council Reg" to "INC India","NCLEX-RN" to "NCSBN USA","OSCE Exam" to "NMC UK"),
                listOf("Clinical Practice Log" to "Document 200 nursing procedures performed during training.","Patient Care Plan" to "Create complete care plans for 10 different medical conditions.","Community Health" to "Conduct community health awareness camp in local area.")),

            Bundle(277,"Physiotherapists (BPT - Bachelor of Physiotherapy) help patients recover from injuries, surgeries, strokes and chronic pain using exercises, manual therapy and electrotherapy. 4-year degree.",
                "After BPT pursue MPT for specialisation in sports, neuro or ortho. Work in hospitals, sports teams, rehab centres or start your own clinic. Growing demand with sports injuries increasing.",
                listOf("Anatomy & Biomechanics","Exercise Therapy","Manual Therapy Techniques","Electrotherapy","Neurological Rehabilitation","Sports Injury Management"),
                listOf("BPT Degree" to "University","MPT Specialisation" to "University","Indian Association of PT" to "IAPT India"),
                listOf("Patient Assessment" to "Practice complete physiotherapy assessment on 20 patients.","Exercise Program" to "Design rehabilitation program for knee injury recovery.","Research Paper" to "Conduct a small clinical study and write findings.")),

            Bundle(280,"Medical Lab Technicians (MLT) perform diagnostic tests including blood tests, urine tests, microbiology cultures and histopathology. Every hospital and diagnostic centre needs qualified MLTs.",
                "Pursue B.Sc MLT (Medical Laboratory Technology). Work in hospitals, pathology labs (SRL, Dr Lal, Thyrocare) or government health centres. Good scope abroad especially in Gulf countries.",
                listOf("Haematology & Blood Analysis","Clinical Biochemistry","Microbiology & Culture","Histopathology","Lab Safety & Quality Control","Medical Equipment Operation"),
                listOf("B.Sc MLT Degree" to "University","DMLT Diploma" to "Paramedical Board","NABL Certified Lab Training" to "NABL India"),
                listOf("Lab Report" to "Analyse and interpret 50 different diagnostic lab reports.","QC Protocol" to "Design quality control protocol for a clinical laboratory.","Equipment Training" to "Get hands-on training on all major lab equipment.")),

            Bundle(284,"BHMS (Bachelor of Homeopathic Medicine and Surgery) is a 5.5-year degree in homeopathy. Homeopathic doctors treat chronic diseases, allergies, skin conditions and stress-related disorders.",
                "Clear NEET UG for BHMS admission. Register with State Homeopathic Council after graduation. Open your own clinic or join a homeopathic hospital. Can practice in Europe after additional qualification.",
                listOf("Homeopathic Materia Medica","Organon of Medicine (Hahnemann)","Case Taking & Repertorization","Homeopathic Pharmacy","Clinical Medicine","Patient Management"),
                listOf("NEET UG" to "NTA India","MD Homeopathy" to "University","Council of Homeopathy Reg" to "CCH India"),
                listOf("Case Studies" to "Document and analyse 50 complete homeopathic case studies.","Materia Medica" to "Master 100 major homeopathic remedies with keynotes.","Community Clinic" to "Conduct free homeopathic consultation camp monthly."))
        )

        for (b in bundles) {
            dao.insertDetail(CareerDetailEntity(nodeId = b.nodeId, description = b.desc, suggestedNextStep = b.next))
            dao.insertSkills(b.skills.map { SkillEntity(nodeId = b.nodeId, skillName = it) })
            dao.insertCertifications(b.certs.map { (n, p) -> CertificationEntity(nodeId = b.nodeId, certName = n, provider = p) })
            dao.insertProjects(b.projects.map { (n, d) -> ProjectEntity(nodeId = b.nodeId, projectName = n, description = d) })
        }
    }
    private suspend fun seedMissingCourses(nodeDao: CareerNodeDao) {
        // NEW ROOT CATEGORIES
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 300, name = "Hotel Management",    parentId = null, type = "ROOT", colorHex = "#B91C1C"),
            CareerNodeEntity(id = 301, name = "Design Courses",      parentId = null, type = "ROOT", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 302, name = "Architecture",        parentId = null, type = "ROOT", colorHex = "#0369A1"),
            CareerNodeEntity(id = 303, name = "Agriculture",         parentId = null, type = "ROOT", colorHex = "#15803D"),
            CareerNodeEntity(id = 304, name = "Law & Legal",         parentId = null, type = "ROOT", colorHex = "#1E3A5F"),
            CareerNodeEntity(id = 305, name = "Media & Journalism",  parentId = null, type = "ROOT", colorHex = "#9D174D"),
            CareerNodeEntity(id = 306, name = "Paramedical",         parentId = null, type = "ROOT", colorHex = "#065F46"),
            CareerNodeEntity(id = 307, name = "Aviation",            parentId = null, type = "ROOT", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 308, name = "Marine & Merchant",   parentId = null, type = "ROOT", colorHex = "#0C4A6E"),
            CareerNodeEntity(id = 309, name = "Social Work",         parentId = null, type = "ROOT", colorHex = "#92400E"),
            CareerNodeEntity(id = 310, name = "Finance & Accounts",  parentId = null, type = "ROOT", colorHex = "#064E3B"),
            CareerNodeEntity(id = 311, name = "Environmental Science",parentId = null, type = "ROOT", colorHex = "#166534"),
            CareerNodeEntity(id = 312, name = "Veterinary Science",  parentId = null, type = "ROOT", colorHex = "#78350F"),
            CareerNodeEntity(id = 313, name = "Library Science",     parentId = null, type = "ROOT", colorHex = "#312E81")
        ))

        // ── HOTEL MANAGEMENT (300) ────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 320, name = "Front Office & Rooms", parentId = 300, type = "BRANCH", colorHex = "#B91C1C"),
            CareerNodeEntity(id = 321, name = "Food & Beverage",      parentId = 300, type = "BRANCH", colorHex = "#B91C1C"),
            CareerNodeEntity(id = 322, name = "Culinary Arts",        parentId = 300, type = "BRANCH", colorHex = "#B91C1C"),
            CareerNodeEntity(id = 323, name = "Hotel Administration", parentId = 300, type = "BRANCH", colorHex = "#B91C1C")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 324, name = "Hotel Front Desk Manager",  parentId = 320, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 325, name = "Guest Relations Executive", parentId = 320, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 326, name = "Housekeeping Manager",      parentId = 320, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 327, name = "Restaurant Manager",        parentId = 321, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 328, name = "Food & Beverage Manager",   parentId = 321, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 329, name = "Bartender / Mixologist",    parentId = 321, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 330, name = "Executive Chef",            parentId = 322, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 331, name = "Pastry Chef",               parentId = 322, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 332, name = "Catering Manager",          parentId = 322, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 333, name = "Hotel General Manager",     parentId = 323, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 334, name = "Event Manager",             parentId = 323, type = "LEAF", colorHex = "#DC2626"),
            CareerNodeEntity(id = 335, name = "Travel & Tourism Manager",  parentId = 323, type = "LEAF", colorHex = "#DC2626")
        ))

        // ── DESIGN COURSES (301) ──────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 340, name = "Fashion & Textile",   parentId = 301, type = "BRANCH", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 341, name = "Industrial Design",   parentId = 301, type = "BRANCH", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 342, name = "Communication Design",parentId = 301, type = "BRANCH", colorHex = "#7C3AED"),
            CareerNodeEntity(id = 343, name = "Jewellery & Craft",   parentId = 301, type = "BRANCH", colorHex = "#7C3AED")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 344, name = "Fashion Designer",         parentId = 340, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 345, name = "Textile Designer",         parentId = 340, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 346, name = "Costume Designer (Films)", parentId = 340, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 347, name = "Product / Industrial Designer",parentId = 341, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 348, name = "Automobile Designer",      parentId = 341, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 349, name = "Packaging Designer",       parentId = 341, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 350, name = "Graphic Designer",         parentId = 342, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 351, name = "UI/UX Designer",           parentId = 342, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 352, name = "Motion Graphics Designer", parentId = 342, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 353, name = "Jewellery Designer",       parentId = 343, type = "LEAF", colorHex = "#8B5CF6"),
            CareerNodeEntity(id = 354, name = "Ceramic & Pottery Artist", parentId = 343, type = "LEAF", colorHex = "#8B5CF6")
        ))

        // ── ARCHITECTURE (302) ────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 360, name = "Architecture Practice",  parentId = 302, type = "BRANCH", colorHex = "#0369A1"),
            CareerNodeEntity(id = 361, name = "Urban & Planning",       parentId = 302, type = "BRANCH", colorHex = "#0369A1"),
            CareerNodeEntity(id = 362, name = "Landscape & Green",      parentId = 302, type = "BRANCH", colorHex = "#0369A1")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 363, name = "Architect (B.Arch)",        parentId = 360, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 364, name = "Interior Architect",        parentId = 360, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 365, name = "Green Building Designer",   parentId = 360, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 366, name = "Urban Planner",             parentId = 361, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 367, name = "Smart City Consultant",     parentId = 361, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 368, name = "Landscape Architect",       parentId = 362, type = "LEAF", colorHex = "#0284C7"),
            CareerNodeEntity(id = 369, name = "Environmental Planner",     parentId = 362, type = "LEAF", colorHex = "#0284C7")
        ))

        // ── AGRICULTURE (303) ─────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 370, name = "Agricultural Sciences",   parentId = 303, type = "BRANCH", colorHex = "#15803D"),
            CareerNodeEntity(id = 371, name = "Agri Business & Tech",    parentId = 303, type = "BRANCH", colorHex = "#15803D"),
            CareerNodeEntity(id = 372, name = "Horticulture & Food",     parentId = 303, type = "BRANCH", colorHex = "#15803D")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 373, name = "Agricultural Scientist",   parentId = 370, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 374, name = "Soil Scientist",           parentId = 370, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 375, name = "Plant Pathologist",        parentId = 370, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 376, name = "AgriTech Entrepreneur",    parentId = 371, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 377, name = "Agricultural Officer",     parentId = 371, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 378, name = "Food Technologist",        parentId = 372, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 379, name = "Horticulturist",           parentId = 372, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 380, name = "Organic Farming Expert",   parentId = 372, type = "LEAF", colorHex = "#16A34A")
        ))

        // ── AVIATION (307) ────────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 390, name = "Pilot & Flying",         parentId = 307, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 391, name = "Cabin Crew & Ground",    parentId = 307, type = "BRANCH", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 392, name = "Aviation Engineering",   parentId = 307, type = "BRANCH", colorHex = "#1D4ED8")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 393, name = "Commercial Pilot (CPL)",   parentId = 390, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 394, name = "Air Traffic Controller",   parentId = 390, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 395, name = "Flight Instructor",        parentId = 390, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 396, name = "Cabin Crew / Air Hostess", parentId = 391, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 397, name = "Airport Ground Staff",     parentId = 391, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 398, name = "Aircraft Maintenance Engg",parentId = 392, type = "LEAF", colorHex = "#2563EB"),
            CareerNodeEntity(id = 399, name = "Aeronautical Engineer",    parentId = 392, type = "LEAF", colorHex = "#2563EB")
        ))

        // ── MEDIA & JOURNALISM (305) ──────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 400, name = "Journalism",             parentId = 305, type = "BRANCH", colorHex = "#9D174D"),
            CareerNodeEntity(id = 401, name = "Film & Television",      parentId = 305, type = "BRANCH", colorHex = "#9D174D"),
            CareerNodeEntity(id = 402, name = "Digital & Social Media", parentId = 305, type = "BRANCH", colorHex = "#9D174D")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 403, name = "Print Journalist",         parentId = 400, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 404, name = "TV News Anchor",           parentId = 400, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 405, name = "Investigative Journalist", parentId = 400, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 406, name = "Photojournalist",          parentId = 400, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 407, name = "Film Producer",            parentId = 401, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 408, name = "Video Editor",             parentId = 401, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 409, name = "Documentary Filmmaker",    parentId = 401, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 410, name = "Social Media Manager",     parentId = 402, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 411, name = "Podcast Creator",          parentId = 402, type = "LEAF", colorHex = "#BE185D"),
            CareerNodeEntity(id = 412, name = "Digital Content Strategist",parentId = 402, type = "LEAF", colorHex = "#BE185D")
        ))

        // ── PARAMEDICAL (306) ─────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 420, name = "Emergency & Critical Care",parentId = 306, type = "BRANCH", colorHex = "#065F46"),
            CareerNodeEntity(id = 421, name = "Optical & Dental Para",    parentId = 306, type = "BRANCH", colorHex = "#065F46"),
            CareerNodeEntity(id = 422, name = "Mental Health Para",       parentId = 306, type = "BRANCH", colorHex = "#065F46")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 423, name = "Emergency Medical Tech (EMT)", parentId = 420, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 424, name = "ICU Technician",               parentId = 420, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 425, name = "Operation Theatre Tech",       parentId = 420, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 426, name = "Optometrist",                  parentId = 421, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 427, name = "Dental Technician",            parentId = 421, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 428, name = "Psychiatric Social Worker",    parentId = 422, type = "LEAF", colorHex = "#059669"),
            CareerNodeEntity(id = 429, name = "Clinical Psychologist",        parentId = 422, type = "LEAF", colorHex = "#059669")
        ))

        // ── SOCIAL WORK (309) ─────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 430, name = "Community Development",  parentId = 309, type = "BRANCH", colorHex = "#92400E"),
            CareerNodeEntity(id = 431, name = "Policy & Research",      parentId = 309, type = "BRANCH", colorHex = "#92400E")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 432, name = "Social Worker (MSW)",    parentId = 430, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 433, name = "Community Organiser",    parentId = 430, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 434, name = "Child Welfare Officer",  parentId = 430, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 435, name = "Social Policy Analyst",  parentId = 431, type = "LEAF", colorHex = "#B45309"),
            CareerNodeEntity(id = 436, name = "NGO Programme Manager",  parentId = 431, type = "LEAF", colorHex = "#B45309")
        ))

        // ── FINANCE & ACCOUNTS (310) ──────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 440, name = "Accounting & Audit",     parentId = 310, type = "BRANCH", colorHex = "#064E3B"),
            CareerNodeEntity(id = 441, name = "Insurance & Actuarial",  parentId = 310, type = "BRANCH", colorHex = "#064E3B"),
            CareerNodeEntity(id = 442, name = "Stock Market & Trading", parentId = 310, type = "BRANCH", colorHex = "#064E3B")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 443, name = "Accountant (CPA/CA)",       parentId = 440, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 444, name = "Internal Auditor",          parentId = 440, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 445, name = "Tax Consultant",            parentId = 440, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 446, name = "Actuary",                   parentId = 441, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 447, name = "Insurance Underwriter",     parentId = 441, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 448, name = "Stock Trader / Analyst",    parentId = 442, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 449, name = "SEBI Registered Advisor",   parentId = 442, type = "LEAF", colorHex = "#047857"),
            CareerNodeEntity(id = 450, name = "Mutual Fund Distributor",   parentId = 442, type = "LEAF", colorHex = "#047857")
        ))

        // ── ENVIRONMENTAL SCIENCE (311) ───────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 455, name = "Env Science & Research",  parentId = 311, type = "BRANCH", colorHex = "#166534"),
            CareerNodeEntity(id = 456, name = "Renewable Energy",        parentId = 311, type = "BRANCH", colorHex = "#166534")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 457, name = "Environmental Scientist",   parentId = 455, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 458, name = "Wildlife Conservationist",  parentId = 455, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 459, name = "Climate Change Analyst",    parentId = 455, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 460, name = "Solar Energy Engineer",     parentId = 456, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 461, name = "Wind Energy Specialist",    parentId = 456, type = "LEAF", colorHex = "#16A34A"),
            CareerNodeEntity(id = 462, name = "Sustainability Consultant", parentId = 456, type = "LEAF", colorHex = "#16A34A")
        ))

        // ── VETERINARY SCIENCE (312) ──────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 465, name = "Veterinary Practice",    parentId = 312, type = "BRANCH", colorHex = "#78350F"),
            CareerNodeEntity(id = 466, name = "Animal Science",         parentId = 312, type = "BRANCH", colorHex = "#78350F")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 467, name = "Veterinary Doctor (BVSc)", parentId = 465, type = "LEAF", colorHex = "#92400E"),
            CareerNodeEntity(id = 468, name = "Zoo Veterinarian",         parentId = 465, type = "LEAF", colorHex = "#92400E"),
            CareerNodeEntity(id = 469, name = "Animal Nutritionist",      parentId = 466, type = "LEAF", colorHex = "#92400E"),
            CareerNodeEntity(id = 470, name = "Wildlife Biologist",       parentId = 466, type = "LEAF", colorHex = "#92400E")
        ))

        // ── MARINE (308) ──────────────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 475, name = "Merchant Navy",          parentId = 308, type = "BRANCH", colorHex = "#0C4A6E"),
            CareerNodeEntity(id = 476, name = "Marine Engineering",     parentId = 308, type = "BRANCH", colorHex = "#0C4A6E")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 477, name = "Merchant Navy Officer",   parentId = 475, type = "LEAF", colorHex = "#0369A1"),
            CareerNodeEntity(id = 478, name = "Ship Captain",            parentId = 475, type = "LEAF", colorHex = "#0369A1"),
            CareerNodeEntity(id = 479, name = "Marine Engineer",         parentId = 476, type = "LEAF", colorHex = "#0369A1"),
            CareerNodeEntity(id = 480, name = "Naval Architect",         parentId = 476, type = "LEAF", colorHex = "#0369A1")
        ))

        // ── LIBRARY SCIENCE (313) ─────────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 485, name = "Library & Info Science",  parentId = 313, type = "BRANCH", colorHex = "#312E81")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 486, name = "Librarian (B.Lib/M.Lib)", parentId = 485, type = "LEAF", colorHex = "#4338CA"),
            CareerNodeEntity(id = 487, name = "Digital Archivist",        parentId = 485, type = "LEAF", colorHex = "#4338CA"),
            CareerNodeEntity(id = 488, name = "Knowledge Manager",        parentId = 485, type = "LEAF", colorHex = "#4338CA")
        ))
        // LAW & LEGAL (304)
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 490, name = "Undergraduate Law",    parentId = 304, type = "BRANCH", colorHex = "#1E3A5F"),
            CareerNodeEntity(id = 491, name = "Postgraduate Law",     parentId = 304, type = "BRANCH", colorHex = "#1E3A5F"),
            CareerNodeEntity(id = 492, name = "Legal Practice Areas", parentId = 304, type = "BRANCH", colorHex = "#1E3A5F")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id = 493, name = "BA LLB (5 Year)",      parentId = 490, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 494, name = "LLB (3 Year)",         parentId = 490, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 495, name = "BBA LLB",              parentId = 490, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 496, name = "LLM (Master of Laws)", parentId = 491, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 497, name = "PhD in Law",           parentId = 491, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 498, name = "Corporate Lawyer",     parentId = 492, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 499, name = "Criminal Lawyer",      parentId = 492, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 500, name = "Cyber Law Specialist", parentId = 492, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 501, name = "Judicial Services",    parentId = 492, type = "LEAF", colorHex = "#1D4ED8"),
            CareerNodeEntity(id = 502, name = "Legal Advisor",        parentId = 492, type = "LEAF", colorHex = "#1D4ED8")
        ))
    }

    private suspend fun seedMissingCourseDetails(dao: CareerDetailDao) {
        data class B(
            val id: Int, val desc: String, val next: String,
            val skills: List<String>,
            val certs: List<Pair<String,String>>,
            val projs: List<Pair<String,String>>
        )
        val list = listOf(
            B(324,"Hotel Front Desk Managers oversee check-in, check-out and guest services in 5-star hotels. They are the face of the property and ensure every guest has a memorable experience.",
                "Complete BHM from IHM. Work at Taj, Oberoi or Marriott. Cruise ship hospitality offers high international salaries.",
                listOf("Guest Relations","Opera/Fidelio PMS","Communication Skills","Conflict Resolution","Upselling","Foreign Language"),
                listOf("NCHMCT JEE" to "NTA India","IHM Degree" to "NCHMCT","Front Desk Rep Cert" to "AHLEI"),
                listOf("Check-in Practice" to "Practice full hotel check-in procedures.","Complaint Role-play" to "Role-play 10 guest complaint scenarios.","Revenue Report" to "Create daily rooms revenue report.")),
            B(330,"Executive Chefs lead the kitchen in 5-star hotels creating menus, managing food costs and training junior chefs to deliver consistent high-quality cuisine.",
                "Complete BHM with culinary specialisation or Culinary Arts degree. Work up from Commis Chef. International experience elevates career.",
                listOf("Classical Cooking Techniques","Menu Planning","Food Costing","Kitchen Management","HACCP & Food Safety","Plating & Presentation"),
                listOf("NCHMCT JEE" to "NTA India","Le Cordon Bleu Diploma" to "Le Cordon Bleu","HACCP Certification" to "NSF International"),
                listOf("Tasting Menu" to "Design and prepare a 7-course tasting menu.","Food Cost Sheet" to "Calculate food cost for 20 dishes.","Recipe Book" to "Create professional recipe book with 50 original dishes.")),
            B(331,"Pastry Chefs specialise in desserts, bread, chocolates and cakes for luxury hotels, standalone patisseries and weddings.",
                "Study pastry arts at WGSHA or IHM. Build Instagram presence showing your creations. Work at 5-star bakeries then start own patisserie.",
                listOf("Baking & Pastry Techniques","Chocolate Tempering","Sugar Art","Bread Making","Cake Decoration","French Pastry Classics"),
                listOf("City & Guilds Pastry" to "City & Guilds UK","Le Cordon Bleu Patisserie" to "Le Cordon Bleu","Gelato University" to "Carpigiani"),
                listOf("Wedding Cake" to "Design and bake a 3-tier fondant wedding cake.","Chocolate Showpiece" to "Create a large chocolate showpiece.","French Pastry" to "Make one classic French pastry each day for a week.")),
            B(333,"Hotel General Managers run entire properties overseeing all departments including rooms, food & beverage, sales, HR and finance.",
                "Start from any hotel department, progress to department head then GM. MBA in Hospitality Management accelerates promotion.",
                listOf("Hotel Operations","Revenue Management","Team Leadership","P&L Management","Sales & Marketing","Brand Compliance"),
                listOf("IHM Degree" to "NCHMCT India","MBA Hospitality" to "IIHMR/Amity","CRME Cert" to "HSMAI"),
                listOf("Revenue Model" to "Build hotel revenue and occupancy forecast.","SOP Manual" to "Write SOPs for a hotel department.","Guest Survey" to "Design and analyse guest satisfaction survey.")),
            B(334,"Event Managers plan weddings, corporate events, product launches, concerts and exhibitions. India events industry is worth 10000+ crores.",
                "Pursue BHM or Event Management degree. Join Wizcraft or Cineyug. Build portfolio and client base starting with college events.",
                listOf("Event Planning & Logistics","Vendor Management","Budget Management","On-Ground Coordination","Client Communication","Marketing & Promotions"),
                listOf("BHM Events" to "IHM India","CPCE Cert" to "NACE USA","Event Mgmt Diploma" to "EMDI Institute"),
                listOf("College Fest" to "Plan and execute your college annual fest.","Wedding Plan" to "Create complete wedding planning timeline and budget.","Vendor Database" to "Build database of 50 verified event vendors.")),
            B(335,"Travel & Tourism Managers create travel packages and manage tourist experiences for travel agencies, tour operators and airlines.",
                "Pursue BHM with Tourism specialisation. Join MakeMyTrip, Thomas Cook or state tourism departments.",
                listOf("Tour Package Design","GDS Systems Amadeus/Galileo","Geography & Culture","Customer Service","Foreign Languages","Marketing"),
                listOf("IATA Travel Agency" to "IATA","NCHMCT Tourism" to "NTA India","Tourism Board Cert" to "Ministry of Tourism"),
                listOf("Tour Package" to "Design a complete 7-day tour package with pricing.","Destination Guide" to "Create detailed travel guide for a tourist destination.","GDS Booking" to "Practice making bookings on Amadeus system.")),
            B(344,"Fashion Designers create clothing, accessories and footwear for retail, haute couture and films. India has world-class designers like Manish Malhotra and Sabyasachi.",
                "Join NIFT, NID or Pearl Academy through entrance exam. Intern with leading designers. Launch your own label after 3-5 years experience.",
                listOf("Fashion Illustration","Pattern Making & Draping","Garment Construction","Textile Knowledge","Trend Forecasting","Fashion Business"),
                listOf("NIFT Entrance" to "NIFT India","NID Entrance" to "NID Ahmedabad","Fashion Design Degree" to "Pearl Academy"),
                listOf("Collection" to "Design and stitch a 5-piece mini fashion collection.","Trend Report" to "Create a seasonal fashion trend report.","Fashion Show" to "Organise a college fashion show with student designs.")),
            B(345,"Textile Designers create patterns, weaves and prints for fabrics used in fashion, home furnishing and industrial applications. India is a global leader in textile exports.",
                "Join NIFT, NID or textile engineering colleges. Work with textile mills, fashion brands or start your own fabric design studio.",
                listOf("Weave Structures","Surface Ornamentation","CAD for Textiles","Colour Theory","Traditional Indian Textiles","Fabric Testing"),
                listOf("NIFT Textile Design" to "NIFT India","NID Textile" to "NID Ahmedabad","Handloom Board Cert" to "Ministry of Textiles"),
                listOf("Fabric Collection" to "Design and develop a 10-sample fabric collection.","Block Print" to "Create original block printing designs on fabric.","Textile Story" to "Document making of a traditional Indian handloom.")),
            B(347,"Product and Industrial Designers create the form, function and aesthetics of manufactured products from furniture to smartphones and medical devices.",
                "Join NID, CEPT or top engineering colleges with design courses. Build diverse portfolio. Target Tata Elxsi, Godrej Design Lab or international firms.",
                listOf("Design Thinking","CAD Modelling SolidWorks/Rhino","Ergonomics","Prototyping","Material Science","User Research"),
                listOf("NID Product Design" to "NID Ahmedabad","CEPT Design" to "CEPT University","Autodesk Fusion 360" to "Autodesk"),
                listOf("Product Redesign" to "Redesign a household product improving its ergonomics.","Prototype" to "Build physical prototype of your product design.","User Study" to "Conduct user research for a new product concept.")),
            B(363,"Architects design buildings, spaces and environments that are functional, safe, sustainable and aesthetically pleasing. B.Arch is a 5-year professional degree.",
                "Clear NATA exam for B.Arch admission. Register with Council of Architecture after degree. Work with firms or start own practice after 5 years.",
                listOf("Architectural Drawing & CAD","AutoCAD & Revit BIM","Structural Systems","Building Materials","Environmental Design","Urban Design"),
                listOf("NATA Exam" to "CoA India","B.Arch Degree" to "University","CoA Registration" to "Council of Architecture"),
                listOf("Design Project" to "Complete a full architectural design project with drawings.","3D Model" to "Build detailed 3D model of your design.","Site Analysis" to "Conduct complete site analysis report for a real location.")),
            B(366,"Urban Planners design city layouts including land use, transportation, housing, public spaces and infrastructure for sustainable growth.",
                "Pursue B.Planning or M.Planning after engineering or architecture. Work with RERA, Smart City Mission or municipal corporations.",
                listOf("Urban Planning Theory","GIS & Remote Sensing","Land Use Planning","Transportation Planning","Environmental Impact Assessment","Building Byelaws"),
                listOf("CoA Town Planning" to "Council of Architecture","GIS Professional" to "ESRI","Smart City Cert" to "MoHUA India"),
                listOf("Master Plan" to "Prepare a simplified master plan for a small town area.","GIS Map" to "Create GIS-based land use map using QGIS.","Traffic Study" to "Conduct traffic volume count at an intersection.")),
            B(373,"Agricultural Scientists conduct research to improve crop yields, develop disease-resistant varieties and create sustainable farming methods.",
                "Pursue B.Sc Agriculture from SAUs then M.Sc and PhD. Join ICAR research institutes or agri input companies like Bayer, BASF, Syngenta.",
                listOf("Crop Science & Agronomy","Plant Breeding & Genetics","Soil Science","Pest Management","Research Methodology","Agricultural Statistics"),
                listOf("ICAR-JRF" to "ICAR India","GATE Agriculture" to "IIT/IISC","PhD Agriculture" to "SAU/IARI"),
                listOf("Crop Trial" to "Conduct field trial comparing two crop varieties.","Soil Report" to "Prepare soil health card for a local farm.","Research Paper" to "Write paper on a local agricultural problem.")),
            B(376,"AgriTech Entrepreneurs use drones, AI, IoT sensors and satellite imagery to modernise Indian farming. One of the most promising startup sectors.",
                "Combine agriculture knowledge with technology skills. Apply to ICAR-NAARM or MANAGE incubators. Access PM-KUSUM and other govt schemes.",
                listOf("Drone Operation & Mapping","IoT Sensor Networks","Farm Management Software","Business Development","Digital Marketing","Financial Modelling"),
                listOf("DGCA Drone Pilot" to "DGCA India","Startup India Reg" to "DPIIT","AgriTech Accelerator" to "ICAR/MANAGE"),
                listOf("Farm App" to "Build a mobile app solving one specific farmer problem.","Drone Mapping" to "Map a farmland using drone and create NDVI report.","Startup Pitch" to "Present agritech idea to agricultural university experts.")),
            B(378,"Food Technologists develop new food products, improve preservation methods and ensure food safety for companies and government labs.",
                "Pursue B.Tech Food Technology or B.Sc Food Science. Work with Nestle, Britannia, Amul, ITC Foods or FSSAI government labs.",
                listOf("Food Chemistry & Microbiology","Food Processing Technology","Quality Control & HACCP","Food Safety Regulations","New Product Development","Sensory Evaluation"),
                listOf("GATE Food Technology" to "IIT/IISC","FSSAI Food Safety" to "FSSAI India","ISO 22000 Lead Auditor" to "BSI Group"),
                listOf("New Product" to "Develop and test a new food product from concept to prototype.","QC Report" to "Perform complete quality control analysis of a food product.","HACCP Plan" to "Create HACCP plan for a food production process.")),
            B(393,"Commercial Pilots fly passenger aircraft for airlines like IndiGo, Air India and SpiceJet. India needs 10000 plus new pilots in the next decade.",
                "Get Class 1 Medical. Complete CPL from DGCA approved flying school. Log 200 plus hours. Clear DGCA exams. Get type rating on A320 or B737.",
                listOf("Meteorology & Weather","Aviation Navigation","Aircraft Systems","ATC Communication","Flight Planning","DGCA Regulations"),
                listOf("DGCA CPL" to "DGCA India","ATPL Theory" to "DGCA India","Type Rating A320" to "Airline Training Centre"),
                listOf("Flight Simulator" to "Complete 50 hours on certified flight simulator.","Navigation Planning" to "Plan a complete IFR flight from Mumbai to Delhi.","DGCA Exam" to "Clear all 9 DGCA ground theory examinations.")),
            B(394,"Air Traffic Controllers guide aircraft safely through airways and at airports. One of the most critical and well-paid aviation careers with excellent job security.",
                "Clear AAI recruitment exam. Train at CATC Allahabad. Excellent salary and benefits in Airports Authority of India.",
                listOf("Radar & ATC Systems","Aviation Phraseology","Emergency Procedures","Situational Awareness","Geography of Airways","ICAO English Level 6"),
                listOf("AAI ATC Recruitment" to "AAI India","ICAO English" to "ICAO","CATC Training" to "Civil Aviation Training College"),
                listOf("ATC Simulation" to "Practice ATC communication on online simulators.","Aviation English" to "Achieve ICAO English Level 5 proficiency.","Airspace Study" to "Study and map complete Indian airspace structure.")),
            B(396,"Cabin Crew ensure passenger safety and comfort on flights. They conduct safety demonstrations, serve food and handle emergencies representing the airline brand.",
                "Join airline cabin crew training programs. SpiceJet, IndiGo, Air India recruit directly. Height and BMI criteria apply. Salary 4-12 LPA.",
                listOf("Passenger Safety Procedures","Emergency Evacuation","First Aid & CPR","In-Flight Service","Communication & Grooming","Foreign Language Basics"),
                listOf("DGCA Cabin Crew Cert" to "DGCA India","First Aid & CPR" to "Red Cross","Airline Training" to "Airline Directly"),
                listOf("Safety Demo" to "Practice complete aircraft safety demonstration.","First Aid" to "Complete certified first aid and CPR training.","Grooming" to "Prepare cabin crew grooming and presentation portfolio.")),
            B(398,"Aircraft Maintenance Engineers inspect, maintain and certify aircraft are airworthy before every flight. Highly regulated well-paid career with global opportunities.",
                "Complete AME course from DGCA-approved institution. Get B1 mechanical or B2 avionics license. Work with airlines, MRO companies or DGCA.",
                listOf("Aircraft Systems Mechanical/Avionics","DGCA Regulations","Maintenance Documentation","Engine Overhaul","Avionics Testing","Safety Management"),
                listOf("DGCA AME License" to "DGCA India","EASA Part 66" to "EASA Europe","AME Training" to "DGCA Approved School"),
                listOf("Maintenance Log" to "Complete aircraft maintenance log entries for training.","Component Study" to "Study and document operation of 20 aircraft components.","Safety Audit" to "Conduct mock safety audit of maintenance procedures.")),
            B(403,"Print Journalists research, investigate and write news stories for newspapers, magazines and news websites. Quality journalism is more important than ever.",
                "Pursue BA Journalism or Mass Communication. Intern at newspapers like Times of India or The Hindu. IIMC Delhi is the top journalism school.",
                listOf("News Writing & Reporting","Investigative Journalism","Feature Writing","Photography","Digital Publishing","Media Law & Ethics"),
                listOf("PG Diploma Journalism" to "IIMC Delhi","Press Card" to "Press Council of India","Google News Initiative" to "Google"),
                listOf("News Articles" to "Write and publish 20 news articles on any platform.","Investigative Story" to "Research and write an investigative feature on a local issue.","Press Release" to "Write professional press releases for 5 organisations.")),
            B(404,"TV News Anchors present news bulletins, host debate shows and conduct interviews on television news channels requiring excellent communication and current affairs knowledge.",
                "Study Mass Communication at IIMC or Symbiosis. Build video showreel. Intern at news channels. Regional language anchors are in high demand.",
                listOf("On-Camera Presentation","Voice Modulation","Script Reading","Current Affairs","Interview Techniques","Media Makeup & Grooming"),
                listOf("IIMC Electronic Media" to "IIMC Delhi","AAJTAK Academy" to "India Today Group","Broadcast Journalism" to "NDTV Academy"),
                listOf("News Showreel" to "Create professional 5-minute news anchor showreel.","Live Debate" to "Host a live debate on a current affairs topic.","Interview" to "Conduct and edit 3 professional video interviews.")),
            B(408,"Video Editors assemble raw footage into polished films, documentaries, advertisements and YouTube videos using Premiere Pro and DaVinci Resolve.",
                "Master Premiere Pro and After Effects. Build portfolio of edited videos. Freelance on Fiverr. Join post-production houses or OTT platforms.",
                listOf("Adobe Premiere Pro","DaVinci Resolve","After Effects & Motion Graphics","Color Grading","Sound Design","Storytelling Through Editing"),
                listOf("Adobe Certified Expert" to "Adobe","DaVinci Resolve Cert" to "Blackmagic Design","YouTube Creator Academy" to "YouTube/Google"),
                listOf("Short Film Edit" to "Edit a complete 10-minute short film from raw footage.","Commercial" to "Edit a 30-second product advertisement video.","Documentary" to "Edit a 20-minute documentary with colour grade and sound mix.")),
            B(410,"Social Media Managers create and execute social media strategies for brands across Instagram, YouTube, LinkedIn and Twitter to build audience and drive business goals.",
                "Get Google Digital Marketing and Meta Blueprint certifications. Build your own social media presence. Target agencies, startups or go freelance.",
                listOf("Content Strategy","Social Media Platforms","Copywriting","Analytics & Reporting","Paid Social Advertising","Community Management"),
                listOf("Meta Blueprint Cert" to "Meta","HubSpot Social Media" to "HubSpot","Google Analytics 4" to "Google"),
                listOf("Brand Account" to "Grow a brand social media account to 1000 followers in 30 days.","Content Calendar" to "Create 3-month social media content calendar with captions.","Analytics Report" to "Create monthly social media performance analytics report.")),
            B(423,"Emergency Medical Technicians provide immediate medical care during accidents, cardiac arrests and disasters. They work in ambulances and emergency departments.",
                "Complete EMT or Paramedic diploma from recognised institutions. Work with 108 ambulance services, hospitals or NDRF. Good scope in Gulf and UK.",
                listOf("CPR & Basic Life Support","Emergency Drug Administration","Patient Assessment & Triage","Immobilisation Techniques","IV Cannulation","Medical Equipment Operation"),
                listOf("EMT Basic Cert" to "Ministry of Health","BLS Provider" to "American Heart Association","ITLS Cert" to "International Trauma Life Support"),
                listOf("Simulation Training" to "Complete 50 emergency simulation scenarios.","First Responder" to "Volunteer with 108 ambulance service for 1 month.","Case Report" to "Document 20 emergency case reports with interventions.")),
            B(426,"Optometrists examine eyes, diagnose vision problems and prescribe corrective lenses. They also screen for glaucoma, diabetic retinopathy and cataracts.",
                "Pursue B.Optometry 4-year degree. Work in eye hospitals like Vasan Eye Care, Sankara Nethralaya, Aravind or open own optical clinic.",
                listOf("Refraction & Vision Testing","Eye Disease Screening","Contact Lens Fitting","Ophthalmic Instruments","Binocular Vision","Low Vision Rehabilitation"),
                listOf("B.Optometry Degree" to "University","COO Registration" to "Allied Health Council","FACO Fellowship" to "FAAO USA"),
                listOf("Eye Camp" to "Assist in organising free eye checkup camp for 100 people.","Case Study" to "Document 20 optometry cases with diagnoses.","Vision Screening" to "Conduct vision screening at a school for 50 students.")),
            B(429,"Clinical Psychologists assess, diagnose and treat mental health disorders including depression, anxiety, OCD, PTSD and schizophrenia. Mental health awareness in India is creating huge demand.",
                "Complete M.Phil Clinical Psychology regulated by RCI. Register with RCI. Work in hospitals, NGOs or private practice. PhD opens teaching and research roles.",
                listOf("Psychological Assessment","Cognitive Behaviour Therapy CBT","Diagnostic Manual DSM/ICD","Crisis Intervention","Research Methods","Ethics in Practice"),
                listOf("RCI Registration" to "RCI India","M.Phil Clinical Psychology" to "University/NIMHANS","APA Membership" to "American Psychological Association"),
                listOf("Assessment Report" to "Write complete psychological assessment report for a case.","CBT Workbook" to "Develop CBT workbook for anxiety management.","Research Study" to "Conduct small research study on mental health topic.")),
            B(446,"Actuaries use mathematics, statistics and financial theory to assess risk in insurance, pensions and investments. One of India highest paying careers.",
                "Clear Institute of Actuaries of India IAI exams CT series 9 papers then CA/SA series. Extremely challenging but salary exceeds 50 LPA for qualified actuaries.",
                listOf("Probability & Statistics","Financial Mathematics","Insurance & Pension Mathematics","Risk Modelling","Excel & VBA","Actuarial Software R/Python"),
                listOf("IAI CT Exams" to "Institute of Actuaries India","IFoA Fellowship" to "Institute Faculty of Actuaries UK","CFA Level 1" to "CFA Institute"),
                listOf("Mortality Table" to "Build a life mortality table using population data.","Insurance Pricing" to "Calculate premium for a term life insurance product.","Risk Model" to "Build a stochastic risk model for an insurance portfolio.")),
            B(448,"Stock Traders and Equity Analysts research companies, analyse financial data and make investment decisions to generate returns in stock markets and mutual funds.",
                "Get NISM Series 8 and Series 15 certifications. Open a demat account and start with paper trading. Join brokerage firms or mutual funds.",
                listOf("Technical Analysis Charts","Fundamental Analysis","Risk Management","Options & Derivatives","Trading Psychology","Financial Modelling"),
                listOf("NISM Series VIII" to "NISM India","NSE Trading Cert" to "NSE India","CMT Level 1" to "CMT Association USA"),
                listOf("Paper Trading" to "Trade a virtual portfolio of 1 lakh for 3 months and document results.","Stock Report" to "Write detailed fundamental analysis report on 5 companies.","Options Strategy" to "Design and backtest an options trading strategy.")),
            B(457,"Environmental Scientists study the environment, monitor pollution and assess ecological impact of human activities recommending solutions for sustainability.",
                "Pursue M.Sc Environmental Science after B.Sc. Work with MoEFCC, PCB, TERI, environmental consultancies or international organisations like UNEP.",
                listOf("Environmental Chemistry","Ecology & Biodiversity","GIS & Remote Sensing","Environmental Impact Assessment","Pollution Monitoring","Environmental Law"),
                listOf("CSIR NET Environment" to "CSIR India","EIA Consultant Cert" to "MoEFCC India","ISO 14001 Lead Auditor" to "BSI Group"),
                listOf("EIA Report" to "Prepare Environmental Impact Assessment for a hypothetical project.","Water Quality Study" to "Monitor and analyse water quality of a local water body.","Carbon Footprint" to "Calculate and propose carbon footprint reduction plan for your college.")),
            B(460,"Solar Energy Engineers design, install and maintain solar power systems for homes, businesses and grid-scale power plants. India solar sector is growing at 30 percent annually.",
                "Pursue B.Tech Electrical or Mechanical then specialise in solar. Get NABCEP certification. Work with Adani Solar, Tata Solar, Waaree or start solar installation business.",
                listOf("Solar PV System Design","Electrical Engineering Basics","AutoCAD Solar Design","Grid Integration","Solar Financing Models","Energy Storage Systems"),
                listOf("NABCEP PV Associate" to "NABCEP USA","MNRE Solar Cert" to "MNRE India","Tier 1 Solar Installer" to "Industry Body"),
                listOf("Rooftop Design" to "Design a complete 5kW rooftop solar system with layout.","Energy Audit" to "Conduct energy audit and recommend solar solution for a building.","Solar Business" to "Create a complete business plan for a solar installation company.")),
            B(467,"Veterinary Doctors diagnose and treat diseases in animals including pets, livestock, zoo animals and wildlife. India veterinary sector is growing with pet ownership.",
                "Clear ICAR AIEEA or state vet entrance for BVSc & AH 5.5 years. Register with Veterinary Council of India. Work in private clinics or government hospitals.",
                listOf("Animal Anatomy & Physiology","Clinical Examination of Animals","Veterinary Surgery","Animal Nutrition","Infectious Disease Management","One Health Concept"),
                listOf("VCI Registration" to "Veterinary Council India","ICAR AIEEA" to "ICAR India","MVSc Entrance" to "State Agricultural University"),
                listOf("Clinical Cases" to "Document 50 animal cases treated during internship.","Health Camp" to "Organise free animal health camp in rural area.","Research Paper" to "Write paper on a common animal disease in your region.")),
            B(477,"Merchant Navy Officers serve on cargo ships, tankers and cruise ships sailing across the world oceans. One of the highest paying careers with complete global travel.",
                "Join pre-sea training at IMU or MERI. Complete DNS or B.Sc Nautical Science. Appear for MMD exams. Salary ranges from 2-15 lakhs per month at sea.",
                listOf("Navigation & Chartwork","GMDSS Radio Communication","Ship Stability & Cargo","Meteorology & Oceanography","Maritime Law SOLAS/MARPOL","Ship Handling"),
                listOf("MMD Certificate" to "DG Shipping India","STCW Basic Safety" to "Maritime Authority","COC Chief Officer" to "DG Shipping"),
                listOf("Navigation Practice" to "Complete chart work exercises for 10 voyage plans.","GMDSS Training" to "Practice all GMDSS communication procedures.","Safety Drills" to "Participate in fire, abandon ship and MOB drill exercises.")),
            B(479,"Marine Engineers maintain and operate engines, electrical systems and machinery on ships ensuring propulsion and power systems run efficiently.",
                "Complete B.E Marine Engineering from IMU or approved institutes. Pass MMD competency exams. Salary 3-12 lakhs per month as Chief Engineer.",
                listOf("Marine Diesel Engines","Electrical Systems on Ships","Refrigeration & Air Conditioning","Hydraulic Systems","Automation & Controls","Engineering Watchkeeping"),
                listOf("MMD Marine Engineer Cert" to "DG Shipping India","STCW Safety" to "Maritime Authority","Chief Engineer COC" to "DG Shipping"),
                listOf("Engine Room" to "Document complete operation of ship engine room systems.","Maintenance Schedule" to "Create preventive maintenance schedule for main engine.","Emergency Procedures" to "Study and practice all engine room emergency procedures.")),
            B(432,"Social Workers work on frontlines of social issues including poverty, child abuse, domestic violence, addiction, disability and elderly care. A profession with deep human impact.",
                "Pursue MSW from TISS, Delhi School of Social Work or Christ University. Work with NGOs, government social welfare departments or start your own NGO.",
                listOf("Social Work Methods Casework/Group Work","Counselling Skills","Community Development","Research & Documentation","Government Welfare Schemes","Child & Family Welfare"),
                listOf("MSW Degree" to "TISS/University","UGC NET Social Work" to "NTA India","CRSE Certification" to "NASW USA"),
                listOf("Field Project" to "Complete 200 hours field placement at social welfare organisation.","Case Study" to "Document 10 complete social work cases with intervention plans.","Community Survey" to "Conduct needs assessment survey in an underserved community.")),
            B(486,"Librarians manage library collections, help patrons find information, catalogue books and manage digital resources in schools, colleges and corporate libraries.",
                "Pursue B.Lib.I.Sc or M.Lib.I.Sc. Clear UGC NET Library Science for government library jobs. Work in universities, research institutes or corporate knowledge centres.",
                listOf("Library Cataloguing DDC/LC","Library Software Koha/Libsys","Information Literacy","Digital Library Management","Reference Services","Database Management"),
                listOf("B.Lib.I.Sc Degree" to "University","UGC NET Library Science" to "NTA India","Digital Library Cert" to "INFLIBNET India"),
                listOf("Library Catalogue" to "Catalogue 100 books using Dewey Decimal Classification.","Digital Archive" to "Create a digital archive of college newsletters and magazines.","Reference Guide" to "Create a subject-wise research guide for students.")),
            B(493,"BA LLB is a prestigious 5-year integrated law degree offered by National Law Universities through CLAT exam. Combines arts, social sciences and law.",
                "Clear CLAT exam for NLU admission. Top NLUs: NLSIU Bangalore, NALSAR Hyderabad, NLU Delhi. After graduation join top law firms or appear for judicial services.",
                listOf("Constitutional Law","Contract & Tort Law","Criminal Law","International Law","Legal Research & Writing","Moot Court & Advocacy"),
                listOf("CLAT" to "NLU Consortium","Bar Council Enrolment" to "State Bar Council","Judicial Services" to "State PSC"),
                listOf("CLAT Mock Tests" to "Solve 30 CLAT mock papers with time limit.","Case Analysis" to "Analyse 10 landmark Supreme Court judgements in detail.","Internship" to "Complete internship at a reputed law firm and write detailed report.")),
            B(494,"LLB 3-year is the traditional law degree for graduates who want to enter the legal profession. Can be pursued after any bachelor degree.",
                "Enrol with State Bar Council after completing LLB. Intern under senior advocates for 2-3 years. Specialise in corporate, criminal or family law.",
                listOf("Civil Procedure Code","Criminal Procedure Code","Indian Penal Code","Contract Law","Evidence Law","Legal Drafting"),
                listOf("LLB Degree" to "University","Bar Council Enrolment" to "State Bar Council","AIBE Exam" to "Bar Council of India"),
                listOf("Case Diary" to "Maintain diary of 20 court cases attended during internship.","Legal Notice" to "Draft 5 different types of legal notices.","Research Paper" to "Write a legal research paper on current law issue.")),
            B(498,"Corporate Lawyers handle mergers, acquisitions, company law compliance, contracts, IPOs and regulatory matters for corporations. One of the highest paying legal careers.",
                "Join top law firms after NLU or top college LLB. Target AZB & Partners, Cyril Amarchand, Khaitan & Co. LLM in Corporate Law adds significant value.",
                listOf("Company Law & SEBI Regulations","M&A Transactions","Contract Drafting & Negotiation","Intellectual Property","Tax Law","Regulatory Compliance"),
                listOf("ICSI Company Secretary" to "ICSI India","LLM Corporate Law" to "University","SEBI Grade A" to "SEBI India"),
                listOf("M&A Agreement" to "Draft a mock merger agreement between two companies.","Due Diligence" to "Prepare a legal due diligence checklist for company acquisition.","Compliance Report" to "Create a corporate compliance calendar for a listed company.")),
            B(499,"Criminal Lawyers represent accused persons and victims in criminal cases including murder, theft, cybercrime and domestic violence in sessions courts and high courts.",
                "Enrol with Bar Council and start under a senior criminal advocate. Build reputation through successful outcomes. Target sessions courts, high courts and Supreme Court.",
                listOf("Indian Penal Code IPC","Code of Criminal Procedure CrPC","Evidence & Forensics","Bail & Anticipatory Bail","Cross-Examination Techniques","Court Advocacy"),
                listOf("Bar Council Enrolment" to "State Bar Council","AIBE Exam" to "Bar Council India","Criminal Trial Practice" to "State Bar"),
                listOf("Mock Trial" to "Conduct a complete mock criminal trial as defence counsel.","Bail Application" to "Draft bail applications for 5 different offence types.","Case Brief" to "Brief 10 landmark criminal cases of the Supreme Court.")),
            B(501,"Judicial Services Officers including Civil Judges and Magistrates preside over courts and deliver justice. One of the most prestigious and respected careers in India.",
                "Clear State Judicial Services exam PCS-J after LLB. Appear for Higher Judicial Services for District Judge posts.",
                listOf("Civil & Criminal Law","Judgement Writing","Legal Interpretation","Constitutional Law","Evidence & Procedure","Judicial Ethics"),
                listOf("State PCS-J Exam" to "State PSC","HJS Exam" to "High Court","AIBE Exam" to "Bar Council India"),
                listOf("Judgement Writing" to "Write mock judgements for 10 civil and criminal cases.","Previous Papers" to "Solve all PCS-J previous year question papers.","Legal Notes" to "Create comprehensive notes on all major bare acts.")),
            B(502,"Legal Advisors and In-House Counsel work within corporations, banks and government departments providing internal legal advice on contracts, compliance and disputes.",
                "Gain 3-5 years law firm experience then transition to in-house roles. Target TCS, Infosys, Tata, RIL, Mahindra or banks. Salary 8-40 LPA.",
                listOf("Contract Management","Regulatory Compliance","Risk Assessment","Negotiation Skills","Corporate Governance","Legal Research"),
                listOf("LLB Degree" to "University","ICSI CS Exam" to "ICSI India","Compliance Officer Cert" to "ICSI/University"),
                listOf("Contract Review" to "Review and redline 10 different commercial contracts.","Policy Draft" to "Draft a complete HR policy document for a company.","Risk Register" to "Create a legal risk register for a business unit."))
        )
        for (b in list) {
            dao.insertDetail(CareerDetailEntity(nodeId = b.id, description = b.desc, suggestedNextStep = b.next))
            dao.insertSkills(b.skills.map { SkillEntity(nodeId = b.id, skillName = it) })
            dao.insertCertifications(b.certs.map { (n, p) -> CertificationEntity(nodeId = b.id, certName = n, provider = p) })
            dao.insertProjects(b.projs.map { (n, d) -> ProjectEntity(nodeId = b.id, projectName = n, description = d) })
        }
    }
    private suspend fun seedMissingRootChildren(nodeDao: CareerNodeDao) {

        // ── 12TH COMMERCE ROOT (id=3) CHILDREN ──────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=600, name="Accounting & Finance",    parentId=3, type="BRANCH", colorHex="#EC4899"),
            CareerNodeEntity(id=601, name="Business Management",     parentId=3, type="BRANCH", colorHex="#EC4899"),
            CareerNodeEntity(id=602, name="Economics & Banking",     parentId=3, type="BRANCH", colorHex="#EC4899"),
            CareerNodeEntity(id=603, name="Professional Courses",    parentId=3, type="BRANCH", colorHex="#EC4899")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=604, name="CA (Chartered Accountant)",  parentId=600, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=605, name="CMA (Cost Accountant)",       parentId=600, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=606, name="B.Com (Honours)",             parentId=600, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=607, name="M.Com",                       parentId=600, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=608, name="BBA (Business Admin)",        parentId=601, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=609, name="MBA (After Graduation)",      parentId=601, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=610, name="BMS (Business Mgmt Studies)", parentId=601, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=611, name="B.Sc Economics",              parentId=602, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=612, name="Bank PO / Clerk",             parentId=602, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=613, name="Investment Banking",          parentId=602, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=614, name="CS (Company Secretary)",      parentId=603, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=615, name="Actuarial Science",           parentId=603, type="LEAF", colorHex="#DB2777"),
            CareerNodeEntity(id=616, name="CFA (Chartered Fin Analyst)", parentId=603, type="LEAF", colorHex="#DB2777")
        ))

        // ── 12TH ARTS ROOT (id=4) CHILDREN ──────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=620, name="Humanities & Social Sci", parentId=4, type="BRANCH", colorHex="#F59E0B"),
            CareerNodeEntity(id=621, name="Law & Legal Studies",     parentId=4, type="BRANCH", colorHex="#F59E0B"),
            CareerNodeEntity(id=622, name="Design & Fine Arts",      parentId=4, type="BRANCH", colorHex="#F59E0B"),
            CareerNodeEntity(id=623, name="Media & Communication",   parentId=4, type="BRANCH", colorHex="#F59E0B"),
            CareerNodeEntity(id=624, name="Education & Teaching",    parentId=4, type="BRANCH", colorHex="#F59E0B")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=625, name="BA Political Science",     parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=626, name="BA Psychology",            parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=627, name="BA Sociology",             parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=628, name="BA History",               parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=629, name="BA Geography",             parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=630, name="BA English Literature",    parentId=620, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=631, name="BA LLB (5 Year)",          parentId=621, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=632, name="LLB (3 Year)",             parentId=621, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=633, name="Paralegal / Legal Exec",   parentId=621, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=634, name="B.Des (Fine Arts/Design)", parentId=622, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=635, name="BFA (Bachelor Fine Arts)", parentId=622, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=636, name="Animation & VFX",          parentId=622, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=637, name="BA Mass Communication",    parentId=623, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=638, name="BMM (Media Management)",   parentId=623, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=639, name="Journalism (BJMC)",        parentId=623, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=640, name="B.Ed (After Graduation)",  parentId=624, type="LEAF", colorHex="#D97706"),
            CareerNodeEntity(id=641, name="D.El.Ed (Diploma)",        parentId=624, type="LEAF", colorHex="#D97706")
        ))

        // ── DIPLOMA / ITI ROOT (id=5) CHILDREN ───────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=650, name="Engineering Trades",     parentId=5, type="BRANCH", colorHex="#10B981"),
            CareerNodeEntity(id=651, name="IT & Electronics",       parentId=5, type="BRANCH", colorHex="#10B981"),
            CareerNodeEntity(id=652, name="Hospitality & Services", parentId=5, type="BRANCH", colorHex="#10B981"),
            CareerNodeEntity(id=653, name="Medical & Health ITI",   parentId=5, type="BRANCH", colorHex="#10B981")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=654, name="Electrician Trade",        parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=655, name="Fitter Trade",             parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=656, name="Welder Trade",             parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=657, name="Plumber Trade",            parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=658, name="Diesel Mechanic",          parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=659, name="AC & Refrigeration Tech",  parentId=650, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=660, name="COPA (Computer Operator)", parentId=651, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=661, name="Electronic Mechanic",      parentId=651, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=662, name="Instrument Mechanic",      parentId=651, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=663, name="Baker & Confectioner",     parentId=652, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=664, name="Hair & Skin Care",         parentId=652, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=665, name="Sewing Technology",        parentId=652, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=666, name="Health Sanitary Inspector",parentId=653, type="LEAF", colorHex="#059669"),
            CareerNodeEntity(id=667, name="Physiotherapy Technician", parentId=653, type="LEAF", colorHex="#059669")
        ))

        // ── B.SC ROOT (id=7) CHILDREN ────────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=670, name="Life Sciences",           parentId=7, type="BRANCH", colorHex="#14B8A6"),
            CareerNodeEntity(id=671, name="Physical Sciences",       parentId=7, type="BRANCH", colorHex="#14B8A6"),
            CareerNodeEntity(id=672, name="Computer & IT Sciences",  parentId=7, type="BRANCH", colorHex="#14B8A6"),
            CareerNodeEntity(id=673, name="Applied Sciences",        parentId=7, type="BRANCH", colorHex="#14B8A6")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=674, name="B.Sc Biotechnology",     parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=675, name="B.Sc Microbiology",      parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=676, name="B.Sc Zoology",           parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=677, name="B.Sc Botany",            parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=678, name="B.Sc Biochemistry",      parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=679, name="B.Sc Genetics",          parentId=670, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=680, name="B.Sc Physics",           parentId=671, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=681, name="B.Sc Chemistry",         parentId=671, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=682, name="B.Sc Mathematics",       parentId=671, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=683, name="B.Sc Statistics",        parentId=671, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=684, name="B.Sc Computer Science",  parentId=672, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=685, name="B.Sc IT",                parentId=672, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=686, name="B.Sc Data Science",      parentId=672, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=687, name="B.Sc Environmental Sci", parentId=673, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=688, name="B.Sc Nutrition & Diet",  parentId=673, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=689, name="B.Sc Forensic Science",  parentId=673, type="LEAF", colorHex="#0D9488"),
            CareerNodeEntity(id=690, name="B.Sc Agriculture",       parentId=673, type="LEAF", colorHex="#0D9488")
        ))

        // ── B.COM ROOT (id=8) CHILDREN ───────────────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=700, name="Accounting & Taxation",  parentId=8, type="BRANCH", colorHex="#F97316"),
            CareerNodeEntity(id=701, name="Finance & Banking",      parentId=8, type="BRANCH", colorHex="#F97316"),
            CareerNodeEntity(id=702, name="Business & Management",  parentId=8, type="BRANCH", colorHex="#F97316"),
            CareerNodeEntity(id=703, name="Professional Finance",   parentId=8, type="BRANCH", colorHex="#F97316")
        ))
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=704, name="CA (Chartered Accountant)",   parentId=700, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=705, name="CMA (Cost Accountant)",        parentId=700, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=706, name="M.Com (Masters Commerce)",     parentId=700, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=707, name="Income Tax Practitioner",      parentId=700, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=708, name="MBA Finance",                  parentId=701, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=709, name="Bank PO / IBPS",               parentId=701, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=710, name="RBI Grade B Officer",          parentId=701, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=711, name="MBA (Business Admin)",         parentId=702, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=712, name="BBA + MBA (Integrated)",       parentId=702, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=713, name="CS (Company Secretary)",       parentId=703, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=714, name="CFA (Chartered Fin Analyst)",  parentId=703, type="LEAF", colorHex="#EA580C"),
            CareerNodeEntity(id=715, name="Actuarial Science",            parentId=703, type="LEAF", colorHex="#EA580C")
        ))

        // ── PURE SCIENCE 12th (id=66) CHILDREN ───────────────────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=720, name="B.Sc Physics",       parentId=66, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=721, name="B.Sc Chemistry",     parentId=66, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=722, name="B.Sc Mathematics",   parentId=66, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=723, name="B.Sc Statistics",    parentId=66, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=724, name="B.Sc Biotechnology", parentId=66, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=725, name="Integrated MSc",     parentId=66, type="LEAF", colorHex="#6366F1")
        ))

        // ── CIVIL & INFRASTRUCTURE B.Tech (id=82) CHILDREN ───────
        nodeDao.insertAll(listOf(
            CareerNodeEntity(id=730, name="Structural Engineer",          parentId=82, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=731, name="Transportation Engineer",      parentId=82, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=732, name="Geotechnical Engineer",        parentId=82, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=733, name="Water Resources Engineer",     parentId=82, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=734, name="Construction Project Manager", parentId=82, type="LEAF", colorHex="#6366F1"),
            CareerNodeEntity(id=735, name="Urban & Town Planner",         parentId=82, type="LEAF", colorHex="#6366F1")
        ))
    }

    private suspend fun seedNewNodeDetails(dao: CareerDetailDao) {
        data class B(val id: Int, val desc: String, val next: String, val skills: List<String>, val certs: List<Pair<String,String>>, val projs: List<Pair<String,String>>)
        val list = listOf(
            // 12TH COMMERCE
            B(604,"CA is India's most prestigious professional qualification. CAs handle auditing, income tax, GST, company law and financial advisory. Salary ranges from 7 to 40+ lakhs per annum.","Register with ICAI after 12th Commerce. Clear Foundation then Intermediate then Final. Complete 3 years articleship training. Top colleges: SRCC Delhi, Sydenham Mumbai.",listOf("Financial Accounting","Taxation GST & Income Tax","Auditing & Assurance","Corporate Law","Cost Accounting","Financial Analysis"),listOf("CA Foundation" to "ICAI","CA Intermediate" to "ICAI","CA Final" to "ICAI"),listOf("GST Filing Practice" to "Practice filing monthly GSTR-1 and GSTR-3B returns.","Tax Audit" to "Prepare mock Form 3CA/3CD for a small firm.","Financial Model" to "Build a DCF valuation model for a listed company.")),
            B(605,"CMA focuses on cost accounting, management accounting and financial management. CMAs work in manufacturing, banks and government enterprises as cost auditors.","Register with ICMAI. Clear Foundation then Intermediate then Final. Recognized for government cost audits. Top institutes: ICMAI regional chapters nationwide.",listOf("Cost Accounting","Management Accounting","Financial Management","Taxation","Strategic Management","ERP Systems"),listOf("CMA Foundation" to "ICMAI","CMA Intermediate" to "ICMAI","CMA Final" to "ICMAI"),listOf("Cost Sheet" to "Prepare cost sheet for a manufacturing unit.","Budget Analysis" to "Analyse budget vs actual for a company.","Pricing Strategy" to "Suggest pricing for a new product using cost-plus method.")),
            B(606,"B.Com Honours is a 3-year degree in accounting, business law, economics and finance. Foundation for CA, MBA and corporate finance careers. Top colleges: SRCC Delhi, LSR Delhi, Christ University Bangalore.","After B.Com pursue CA, MBA, CMA or M.Com. Join Big 4 accounting firms: Deloitte, PwC, KPMG, EY. Excellent placement at banking and finance companies.",listOf("Financial Accounting","Business Law","Economics","Income Tax","Auditing","Business Communication"),listOf("CA Foundation" to "ICAI","CFA Level 1" to "CFA Institute","MBA Entrance CAT" to "IIM"),listOf("Annual Report Analysis" to "Analyse annual report of a listed company.","GST Calculator" to "Build Excel tool to calculate GST on products.","Business Plan" to "Write complete business plan for a startup.")),
            B(607,"M.Com is a 2-year postgraduate degree in commerce covering advanced accounting, finance, economics and business management. Opens doors to academia, banking and corporate finance.","Pursue M.Com from top universities: Delhi University, Mumbai University, Osmania University. Then UGC NET for teaching or MBA for corporate roles.",listOf("Advanced Accounting","Financial Management","Business Statistics","Corporate Tax","Research Methodology","Econometrics"),listOf("M.Com Entrance" to "University","UGC NET Commerce" to "NTA India","GSET Commerce" to "State Universities"),listOf("Research Paper" to "Write a research paper on a current commerce topic.","Case Study" to "Analyse financial performance of a listed company.","Tax Filing" to "Complete end-to-end income tax return for a business firm.")),
            B(608,"BBA is a 3-year management degree covering marketing, HR, finance, operations and entrepreneurship. Ideal foundation for MBA. Top colleges: Christ University, Symbiosis Pune, NMIMS Mumbai, Amity University.","After BBA pursue MBA from top B-school via CAT, XAT or GMAT. Can also join marketing, sales or operations roles directly. Salary starts at 4-8 LPA.",listOf("Business Management","Marketing Basics","Financial Accounting","Human Resources","Operations Management","Business Communication"),listOf("CAT Exam" to "IIM","Google Digital Marketing" to "Google","IELTS/TOEFL" to "British Council"),listOf("Case Study" to "Analyse a real company case like Zomato IPO.","Marketing Campaign" to "Create a complete digital marketing campaign.","Startup Pitch" to "Present a startup pitch to your classmates.")),
            B(609,"MBA from a top B-school is the most transformative professional qualification. Opens doors to consulting, investment banking, product management and C-suite roles. IIMs produce India's top business leaders.","Crack CAT for IIM admission. Target IIM-A, IIM-B, IIM-C, IIM-L, IIM-K, XLRI, ISB, MDI. Work experience of 2-5 years before MBA dramatically increases placement prospects.",listOf("Strategic Management","Financial Analysis","Marketing Management","Operations","Leadership","Business Analytics"),listOf("CAT Exam" to "IIM","XAT Exam" to "XLRI Jamshedpur","GMAT" to "GMAC Global"),listOf("Business Case" to "Solve 5 Harvard Business School case studies.","B-Plan Competition" to "Participate in national business plan competition.","Consulting Project" to "Complete a real consulting project for a local business.")),
            B(610,"BMS is a 3-year degree focused on business management, marketing, finance and operations. Popular in Mumbai University colleges. Excellent foundation for MBA and corporate careers.","Pursue MBA after BMS from top B-schools. Join marketing, sales, operations or HR roles directly. Popular colleges: HR College Mumbai, NMIMS, St Xavier's Mumbai.",listOf("Business Management","Marketing","Financial Management","Operations","Organisational Behaviour","Business Law"),listOf("CAT Exam" to "IIM","MBA Entrance XAT" to "XLRI","Digital Marketing Cert" to "Google"),listOf("Market Research" to "Conduct market research for a product in your locality.","Business Report" to "Write comprehensive business analysis report.","Event Management" to "Organise a business event from planning to execution.")),
            B(611,"B.Sc Economics studies micro and macroeconomics, econometrics, development economics and policy. Economists work in banks, government, consulting, research and international organisations.","After B.Sc Economics pursue M.Sc Economics or MBA. Crack IES exam for government economist. Top colleges: DSE Delhi, JNU Delhi, Presidency College Kolkata, Gokhale Institute Pune.",listOf("Microeconomics","Macroeconomics","Econometrics","Statistics","Development Economics","Policy Analysis"),listOf("IES Exam" to "UPSC India","M.Sc Economics" to "DSE/JNU","GATE Economics" to "IIT/IISC"),listOf("Economic Survey" to "Summarise key findings from India's Economic Survey.","Market Study" to "Study price changes of vegetables in local market.","Policy Paper" to "Write a policy recommendation on a current economic issue.")),
            B(612,"Bank PO is one of India's most sought-after government jobs. Bank POs manage branches, sanction loans and handle customer relations. Salary 8-15 LPA with excellent benefits and job security.","Clear IBPS PO or SBI PO exam. Practice aptitude, reasoning and English daily. Attempt multiple bank exams simultaneously. Coaching: Oliveboard, Adda247, Bankers Adda.",listOf("Quantitative Aptitude","Logical Reasoning","English Language","Banking Awareness","Computer Basics","Current Affairs"),listOf("IBPS PO" to "IBPS India","SBI PO" to "SBI India","RBI Assistant" to "RBI India"),listOf("Mock Tests" to "Solve 50 full-length bank exam mock tests.","Current Affairs Log" to "Maintain daily banking news notes for 6 months.","Speed Maths" to "Practice 100 aptitude questions daily for accuracy and speed.")),
            B(613,"Investment Bankers advise corporations on mergers, acquisitions, IPOs and raising capital. Work at Goldman Sachs, Morgan Stanley, JP Morgan, Kotak IB. Extraordinary salaries of 20-100+ LPA.","Pursue MBA Finance from IIM/XLRI/ISB. Intern at a leading IB firm during MBA. Network at finance events. CFA alongside MBA dramatically increases credibility.",listOf("Financial Modelling DCF/LBO","Valuation Methods","Capital Markets","M&A Process","Excel & PowerPoint","Pitch Book Creation"),listOf("CFA Level 1-3" to "CFA Institute","FMVA" to "CFI","Bloomberg Market Concepts" to "Bloomberg"),listOf("Pitch Book" to "Create full investment banking pitch book.","M&A Model" to "Build merger model with synergies in Excel.","IPO Analysis" to "Analyse upcoming IPO and value the company.")),
            B(614,"Company Secretary is a key officer managing corporate compliance, board meetings, secretarial practices and regulatory filings. CS professionals are in high demand in all listed companies.","Register with ICSI. Clear Foundation then Executive then Professional programme. Complete training. Top institutes: ICSI Headquarters Delhi plus regional chapters.",listOf("Company Law","Securities Law","Secretarial Practice","Corporate Governance","Drafting & Conveyancing","FEMA & International Law"),listOf("CS Foundation" to "ICSI India","CS Executive" to "ICSI India","CS Professional" to "ICSI India"),listOf("Compliance Calendar" to "Create annual compliance calendar for a listed company.","Board Minutes" to "Draft board meeting minutes and resolutions.","ROC Filing" to "Practice filing annual returns with Ministry of Corporate Affairs.")),
            B(615,"Actuaries use mathematics, statistics and financial theory to assess risk in insurance, pensions and investments. One of India's highest paying careers with salary exceeding 50 LPA.","Clear Institute of Actuaries of India IAI exams. 9 CT series papers then CA/SA series. Top employers: Life Insurance Corporation, Max Life, HDFC Life, KPMG, Deloitte.",listOf("Probability & Statistics","Financial Mathematics","Insurance & Pension Mathematics","Risk Modelling","Excel & VBA","Actuarial Software R/Python"),
            listOf("IAI CT Exams" to "Institute of Actuaries India","IFoA Fellowship" to "Institute Faculty of Actuaries UK","CFA Level 1" to "CFA Institute"),
            listOf("Mortality Table" to "Build a life mortality table using population data.","Insurance Pricing" to "Calculate premium for a term life insurance product.","Risk Model" to "Build a stochastic risk model for an insurance portfolio.")),
            B(616,"CFA is the world's most respected investment management credential. CFA charter holders work as portfolio managers, equity researchers and investment analysts at global financial firms.","Pursue CFA Level 1, 2 and 3 exams while working or after graduation. Top employers: Morgan Stanley, Goldman Sachs, JP Morgan, Motilal Oswal, Kotak Securities.",listOf("Ethical & Professional Standards","Portfolio Management","Equity Valuation","Fixed Income","Derivatives","Alternative Investments"),
            listOf("CFA Level 1" to "CFA Institute USA","CFA Level 2" to "CFA Institute USA","CFA Level 3" to "CFA Institute USA"),
            listOf("Stock Analysis" to "Write CFA-style equity research report on 3 stocks.","Portfolio" to "Build and manage a model investment portfolio for 6 months.","Ethics Case" to "Analyse 10 CFA ethics violation case studies.")),
            // 12TH ARTS
            B(625,"BA Political Science studies government systems, international relations, political theory and public policy. Opens doors to civil services, journalism, NGOs and diplomacy. Top colleges: Hindu College Delhi, Presidency College Kolkata, Loyola Chennai.","Pursue MA Political Science then crack UPSC Civil Services for IAS or IFS. Also good for law, journalism and public policy careers. JNU and Hyderabad University offer excellent MA programmes.",listOf("Political Theory","Indian Constitution","International Relations","Public Policy","Research Methods","Current Affairs"),listOf("UPSC CSE" to "UPSC India","MA Entrance" to "JNU/DU","UGC NET" to "NTA India"),listOf("Policy Analysis" to "Analyse any government scheme and its impact.","Mock Parliament" to "Organise mock parliament debate in college.","Election Study" to "Study voting patterns in a recent election.")),
            B(626,"BA Psychology studies human behaviour, mental processes, emotions, personality and mental disorders. Growing demand as India recognises mental health importance. Top colleges: Christ University, Fergusson College Pune, Lady Shri Ram Delhi.","Pursue MA or M.Sc Psychology then RCI registration for clinical practice. Excellent career in corporate HR, therapy and education. NIMHANS Bangalore is India's premier mental health institute.",listOf("Abnormal Psychology","Cognitive Psychology","Counselling Techniques","Research Methods","Child Psychology","Personality Assessment"),listOf("RCI Registration" to "RCI India","MA Psychology" to "University","UGC NET Psychology" to "NTA India"),listOf("Stress Survey" to "Conduct survey on student stress levels.","Case Study" to "Write psychological case study on a fictional client.","Mental Health Workshop" to "Create workshop on anxiety management techniques.")),
            B(627,"BA Sociology studies society, social institutions, culture, inequality, caste, gender and urbanisation. Sociologists work in NGOs, government policy, social work and journalism. Top colleges: JNU Delhi, Hyderabad University, DU colleges.","Pursue MA Sociology then PhD or crack UPSC for IAS. Also good for social work MSW and NGO careers. TISS Mumbai is the premier institute for social sciences.",listOf("Social Theory","Research Methods","Indian Society","Gender Studies","Urban Sociology","Caste & Inequality"),listOf("UGC NET Sociology" to "NTA India","MA Entrance" to "JNU/Hyderabad Univ","UPSC CSE" to "UPSC India"),listOf("Village Survey" to "Study social issues in village and write report.","Documentary" to "Make short documentary on a social issue.","Policy Brief" to "Write policy brief on social problem in your city.")),
            B(628,"BA History studies ancient, medieval and modern world history, civilisations, empires and social movements. Strong foundation for UPSC, academics, archaeology and museum management. Top colleges: St Stephen's Delhi, Presidency Kolkata, Hindu College.","Pursue MA History from JNU, DU or Hyderabad University. Crack UPSC for history optional. Also good for archaeology, archival research and heritage conservation careers.",listOf("Ancient & Medieval History","Modern Indian History","World History","Historical Research Methods","Archival Studies","Heritage Conservation"),listOf("UPSC History Optional" to "UPSC India","MA History Entrance" to "JNU/University","UGC NET History" to "NTA India"),listOf("Historical Research" to "Write a research paper on a local historical event.","Heritage Walk" to "Organise a heritage walk in your city documenting monuments.","Exhibition" to "Create a photo exhibition on a historical period.")),
            B(629,"BA Geography studies physical geography, human geography, environmental issues, cartography and GIS. Geographers work in urban planning, disaster management, remote sensing and environmental consulting.","Pursue MA Geography then specialise in GIS and Remote Sensing. Work with ISRO, National Remote Sensing Centre, Survey of India or urban planning departments.",listOf("Physical Geography","Human & Urban Geography","Cartography & GIS","Remote Sensing","Environmental Geography","Spatial Analysis"),listOf("MA Geography Entrance" to "JNU/University","GIS Professional Cert" to "ESRI","UGC NET Geography" to "NTA India"),listOf("GIS Map" to "Create a thematic GIS map of your district using QGIS.","Field Study" to "Conduct a geographical field study of a local area.","Environmental Report" to "Write an environmental assessment of your locality.")),
            B(630,"BA English Literature studies English language, literature, poetry, drama and critical theory. Opens careers in writing, journalism, publishing, content creation, academia and corporate communications.","Pursue MA English from top universities. Work as content writer, editor, journalist, publisher or academic. JNU, DU, Hyderabad University and English and Foreign Languages University Hyderabad are top institutes.",listOf("Literary Analysis & Criticism","Creative Writing","Linguistics","Research & Academic Writing","Content Creation","Public Speaking & Rhetoric"),listOf("MA English Entrance" to "University","UGC NET English" to "NTA India","Content Writing Cert" to "HubSpot/Coursera"),listOf("Book Review" to "Write critical reviews of 10 classic novels.","Short Story" to "Write and publish 5 short stories on literary platforms.","Research Paper" to "Write academic paper on a literary theme for college journal.")),
            B(631,"BA LLB is a prestigious 5-year integrated law degree at National Law Universities through CLAT. Combines arts and law education for comprehensive legal training. Top NLUs: NLSIU Bangalore, NALSAR Hyderabad, NLU Delhi.","Clear CLAT exam with high score. Top NLUs offer excellent placement at law firms. After graduation join AZB & Partners, Cyril Amarchand, Khaitan, JSA or appear for judicial services.",listOf("Constitutional Law","Contract & Tort Law","Criminal Law","International Law","Legal Research","Moot Court Skills"),listOf("CLAT" to "NLU Consortium","Bar Council Enrolment" to "State Bar Council","Judicial Services" to "State PSC"),listOf("CLAT Mock Tests" to "Solve 30 CLAT mock papers.","Case Analysis" to "Analyse 10 landmark Supreme Court judgements.","Internship" to "Complete internship at a reputed law firm.")),
            B(632,"LLB 3-year is the traditional law degree for arts, commerce or science graduates who want to enter the legal profession. Can be done after any bachelor degree. Practical litigation-focused training.","Enrol with State Bar Council after LLB. Intern under senior advocates for 2-3 years. Specialise in corporate, criminal, family or property law. DU Campus, GLC Mumbai, Government Law College Trivandrum are premier colleges.",listOf("Civil Procedure Code","Criminal Procedure Code","Indian Penal Code","Contract Law","Evidence Law","Legal Drafting"),listOf("LLB Degree" to "University","Bar Council Enrolment" to "State Bar Council","AIBE Exam" to "Bar Council of India"),listOf("Case Diary" to "Maintain diary of 20 court cases attended during internship.","Legal Notice" to "Draft 5 different types of legal notices.","Moot Court" to "Participate in at least 2 moot court competitions.")),
            B(634,"B.Des is a 4-year undergraduate design degree covering product design, visual communication, textile design, ceramic design or fashion design. Highly creative career with good earning potential.","Join NID Ahmedabad, NIFT Delhi, CEPT University, MIT Institute of Design, Symbiosis Institute of Design through entrance exams. Build strong portfolio.",listOf("Design Thinking & Process","Visual Communication","Sketching & Ideation","3D Modelling","Colour Theory","User Research"),listOf("NID Design Aptitude Test" to "NID India","NIFT Entrance" to "NIFT India","MIT ID Entrance" to "MIT Institute of Design"),listOf("Design Portfolio" to "Create a portfolio of 10 design projects in your specialisation.","User Research" to "Conduct user research for a new product concept.","Exhibition" to "Participate in a design exhibition showcasing your work.")),
            B(635,"BFA Bachelor of Fine Arts is a 4-year degree covering painting, sculpture, printmaking, applied arts and art history. For students passionate about visual arts as a creative and commercial career.","Join Faculty of Fine Arts Baroda, College of Art Delhi, Lalit Kala Akademi affiliated institutions. Build portfolio. Work as artist, art teacher, illustrator or art director.",listOf("Drawing & Painting Techniques","Sculpture & 3D Art","Art History & Criticism","Printmaking","Mixed Media Art","Portfolio Development"),listOf("BFA Entrance" to "University Faculty of Fine Arts","CEED Exam" to "IIT/IISC","UGC NET Fine Arts" to "NTA India"),listOf("Art Exhibition" to "Organise a solo or group art exhibition of your work.","Art Journal" to "Maintain a daily art journal with 100 original artworks.","Mural" to "Create a public mural or art installation in your college.")),
            B(636,"Animation & VFX professionals create animated films, game graphics and visual effects for movies, OTT and advertising. Booming industry with Bollywood, Netflix India, Amazon Prime creating massive content.","Study at Arena Animation, MAAC, Framestore, IIFA Multimedia Bangalore. Build showreel with 3D animation and VFX projects. Target Prime Focus, Prana Studios, DQ Entertainment.",listOf("3D Modelling Blender/Maya","Animation Principles","VFX Compositing After Effects","Motion Graphics","Storyboarding","Texturing & Lighting"),listOf("Adobe Certified Expert" to "Adobe","Autodesk Maya Cert" to "Autodesk","Foundry Nuke Cert" to "Foundry"),listOf("3D Character" to "Model and rig a 3D character in Blender.","Short Animation" to "Create a 30-second animated short film.","VFX Shot" to "Add VFX to a real video using After Effects.")),
            B(637,"BA Mass Communication teaches journalism, advertising, public relations, film production and broadcasting. One of the most versatile arts degrees opening multiple media career paths.","Join IIMC Delhi, Symbiosis Pune, Xavier Institute Mumbai, Manipal Institute of Communication, AJK MCRC Jamia Millia. Intern at media companies from year 1.",listOf("News Writing & Reporting","Video Production","Public Relations","Social Media Management","Advertising","Media Law & Ethics"),listOf("PG Diploma Journalism" to "IIMC Delhi","Google News Initiative" to "Google","PR Certification" to "PRCI India"),listOf("Campus News Blog" to "Start a news blog covering college events.","Short Film" to "Produce a 5-minute short documentary film.","PR Campaign" to "Design a complete PR campaign for a local NGO.")),
            B(638,"BMM Bachelor of Mass Media is Mumbai University's equivalent of mass communication degree. Highly industry-focused with specialisations in advertising, journalism and public relations.","Join HR College, Sophia College, KC College, SIES College all in Mumbai. Strong alumni network in Bollywood and advertising. Intern from semester 3 onwards.",listOf("Media Writing","Advertising & Brand Management","Digital Media","Film & TV Production","Radio Broadcasting","Research Methods"),listOf("BMM Degree" to "Mumbai University","JWT Academy" to "J Walter Thompson","Digital Marketing" to "Google/HubSpot"),listOf("Ad Campaign" to "Create a complete advertising campaign for a brand.","News Package" to "Produce a complete TV news package on a local story.","Social Media" to "Manage social media accounts for a college event.")),
            B(639,"BJMC Bachelor of Journalism and Mass Communication trains students for print, broadcast and digital journalism. One of the fastest routes into newsrooms, digital media and content production.","Join IIMC Delhi, AJK MCRC Jamia Millia, XIC Mumbai, Asian College of Journalism Chennai. Build portfolio of published articles, videos and podcasts from day one.",listOf("Investigative Reporting","Feature Writing","Broadcast Journalism","Digital Journalism","Media Ethics","Data Journalism"),listOf("PG Journalism" to "IIMC Delhi","Press Trust Cert" to "PTI","Asian College Journalism" to "ACJ Chennai"),listOf("Investigation Story" to "Research and publish an investigative story on a local issue.","Podcast" to "Create a 10-episode podcast on a current affairs topic.","Photo Essay" to "Shoot and publish a photo essay on a social issue.")),
            B(640,"B.Ed after graduation is India's standard teacher education degree making graduates eligible to teach in schools. Required for government and private school teaching jobs across India.","Complete B.Ed from NCTE-approved institutions. Clear CTET or State TET for government school eligibility. Top colleges: Delhi University B.Ed, Bombay Teachers Training College, IASE universities.",listOf("Pedagogy of Teaching","Educational Psychology","Curriculum Development","Classroom Management","Assessment & Evaluation","Inclusive Education"),listOf("CTET" to "CBSE India","State TET" to "State Education Board","B.Ed Entrance" to "University/CET"),listOf("Lesson Plans" to "Create 20 detailed lesson plans for different subjects and classes.","Teaching Practice" to "Complete 40 days school teaching practice with feedback.","Educational Research" to "Conduct action research on a teaching-learning problem.")),
            B(641,"D.El.Ed Diploma in Elementary Education is a 2-year teacher training diploma for teaching Classes 1-8. Required for primary school teaching in government schools. Available through NIOS and state institutes.","Complete D.El.Ed and clear CTET Paper 1. Apply for primary school teacher vacancies. Top salary in Kendriya Vidyalaya and Navodaya schools. State government schools also offer excellent job security.",listOf("Child Development & Pedagogy","Teaching Primary Maths","Teaching Primary Language","EVS Environmental Studies","Inclusive Education","School Management"),listOf("D.El.Ed Programme" to "NIOS/State DTE","CTET Paper 1" to "CBSE India","State TET" to "State Education Board"),listOf("Teaching Demo" to "Deliver 10 demo classes at primary school level.","Activity Based Learning" to "Design 20 activity-based learning materials.","Child Study" to "Conduct a detailed developmental study of a primary school child.")),
            // DIPLOMA / ITI
            B(654,"Electrician Trade is a 2-year ITI course to install, maintain and repair electrical wiring, equipment and machines. Always in demand in construction, factories and homes. Salary starts at 15000-40000 per month.","Get Wireman License from State Electricity Board. Work in construction, factories, CPWD or start electrical contracting business. Government apprenticeship at PSUs like BHEL, NTPC, PGCIL available.",listOf("Electrical Wiring","Motor Winding","Panel Board Installation","Earthing & Safety","Electrical Diagrams","Meter Reading"),listOf("NCVT Wireman License" to "State Electricity Board","ITI Certificate" to "NCVT India","CPWD Contractor" to "CPWD India"),listOf("House Wiring" to "Wire a complete model house with switches and fan.","Motor Rewinding" to "Rewind a single phase motor.","Solar Wiring" to "Install a small solar panel with battery backup.")),
            B(655,"Fitter Trade is a 2-year ITI course to assemble, fit and maintain mechanical equipment. Fitters work in factories, railways, defence and manufacturing. Indian Railways and BHEL are major recruiters.","After ITI Fitter join railway apprenticeship RRB, defence production units or large factories like L&T, BHEL, HAL. Can start fabrication workshop. Gulf countries offer good salaries.",listOf("Blueprint Reading","Fitting & Assembly","Lathe Machine Operation","Welding Basics","Measurement Tools","Maintenance & Repair"),listOf("NCVT Certificate" to "NCVT India","RRB Apprentice" to "Indian Railways","BHEL Apprentice" to "BHEL India"),listOf("Metal Bracket" to "Fabricate metal support bracket using workshop tools.","Machine Assembly" to "Disassemble and reassemble a gear box.","Tolerance Study" to "Measure and verify dimensions of machined parts.")),
            B(656,"Welder Trade covers gas welding, arc welding, MIG/TIG welding and pipe welding. High demand in shipbuilding, construction, oil & gas and automobile industries. Gulf countries pay 2-5 lakhs per month.","Work in shipyards like Cochin Shipyard, pipeline projects, automobile companies. Get AWS certification for overseas welding jobs in Gulf, UK and Europe.",listOf("Arc Welding SMAW","MIG/MAG Welding","TIG Welding","Gas Welding","Pipe Welding","Welding Safety"),listOf("NCVT Welding Cert" to "NCVT India","AWS Certified Welder" to "American Welding Society","ASME Cert" to "ASME"),listOf("Steel Gate" to "Weld a complete steel gate for a house.","Pipe Joint" to "Practice TIG welding on stainless steel pipe.","Structural Weld" to "Weld a structural frame and test its strength.")),
            B(657,"Plumber Trade teaches installation and maintenance of water supply, drainage and sanitation systems. Essential in construction, real estate and facility management. Huge demand as India expands infrastructure.","Work with construction companies, housing societies, hotels or start own plumbing business. Dubai and Gulf countries offer excellent salaries for skilled plumbers.",listOf("Water Supply Systems","Drainage & Sanitation","Pipe Fitting & Jointing","Plumbing Tools","Blueprint Reading","Safety Protocols"),listOf("NCVT Plumber Cert" to "NCVT India","ISP Certification" to "Indian Society of Plumbing","Gulf Work Permit" to "Overseas Employment"),listOf("Bathroom Plumbing" to "Complete plumbing work for a model bathroom.","Pipe Layout" to "Design complete plumbing layout for a 2BHK house.","Maintenance Log" to "Create preventive maintenance schedule for plumbing systems.")),
            B(658,"Diesel Mechanic Trade teaches servicing, maintenance and repair of diesel engines used in trucks, buses, tractors, generators and industrial machinery. High demand from transport and agriculture sectors.","Work at authorised service centres, transport companies, agriculture equipment dealers or start own mechanic workshop. Government motor pools like state transport corporations are major employers.",listOf("Diesel Engine Technology","Fuel Injection Systems","Hydraulic Systems","Engine Overhaul","Electrical Systems in Vehicles","Workshop Tools"),listOf("NCVT Diesel Mechanic" to "NCVT India","Apprenticeship" to "Transport Corp/Industry","ASDC Certification" to "Automotive Skill Dev Council"),listOf("Engine Overhaul" to "Completely overhaul a diesel engine under supervision.","Fault Diagnosis" to "Diagnose and repair 10 different diesel engine faults.","Service Report" to "Prepare complete vehicle service history report.")),
            B(659,"AC and Refrigeration Technician Trade teaches installation, servicing and repair of air conditioners, refrigerators and cold chain equipment. High demand with India's cooling market growing rapidly.","Work with AC companies like Voltas, Blue Star, Daikin, Lloyd or start own AC service business. Gulf countries offer excellent salaries. Cold storage industry also growing rapidly.",listOf("Refrigeration Cycle Principles","Electrical Circuits for AC","Gas Charging & Recovery","Inverter AC Technology","Split & Window AC Service","Commercial Refrigeration"),listOf("NCVT AC & Ref Cert" to "NCVT India","BEE Star Rating Cert" to "Bureau of Energy Efficiency","Daikin Training" to "Daikin India"),listOf("AC Installation" to "Install and commission a split AC unit correctly.","Gas Charging" to "Practice gas charging and leak detection on AC systems.","Inverter AC" to "Diagnose and repair a faulty inverter AC unit.")),
            B(660,"COPA Computer Operator and Programming Assistant is a 1-year ITI course covering MS Office, Tally, internet, DTP and basic programming. Opens data entry, office assistant and computer teacher roles.","Work as computer operator in banks, government offices, schools and BPOs. Pursue advanced IT courses to move into software development. Tally certification highly valued.",listOf("MS Office Word/Excel/PowerPoint","Tally ERP","Internet & Email","DTP PageMaker/CorelDraw","Data Entry","Basic Python Programming"),listOf("NCVT COPA Cert" to "NCVT India","Tally Certification" to "Tally Solutions","CCC Certificate" to "NIELIT India"),listOf("Office Automation" to "Create complete office billing system in Excel.","Newsletter" to "Design 4-page college newsletter using CorelDraw.","Database" to "Create student database using MS Access.")),
            // B.SC
            B(674,"B.Sc Biotechnology combines biology and technology to develop medicines, vaccines, GMO crops and diagnostic tools. One of the fastest growing science careers globally. Top colleges: Delhi University, Pune University, VIT Vellore, SRM Chennai.","After B.Sc Biotech pursue M.Sc Biotechnology from reputed universities then PhD. Top recruiters: Biocon Bangalore, Dr Reddy's Hyderabad, Serum Institute Pune, CSIR labs.",listOf("Molecular Biology","Genetic Engineering","Cell Culture","Bioinformatics","PCR Techniques","Research Methodology"),listOf("GATE Biotechnology" to "IIT/IISC","CSIR NET" to "CSIR India","DBT JRF" to "Dept of Biotechnology"),listOf("DNA Extraction" to "Extract DNA from banana in home lab.","Research Review" to "Write review on CRISPR gene editing.","Bioinformatics Project" to "Analyse protein sequences using online tools.")),
            B(675,"B.Sc Microbiology studies microorganisms including bacteria, viruses, fungi and parasites. Opens careers in pharmaceutical industries, food safety, hospitals and research institutions. Top colleges: St Xavier's Mumbai, Christ University, Fergusson College Pune.","Pursue M.Sc Microbiology then PhD for research. Work in pharma companies, food testing labs, hospitals as microbiologist or pursue medical microbiology. CSIR NET opens research fellowships.",listOf("Bacteriology","Virology","Mycology","Clinical Microbiology","Immunology","Microbial Genetics"),listOf("CSIR NET Microbiology" to "CSIR India","GATE Biotech" to "IIT/IISC","M.Sc Entrance" to "University"),listOf("Culture Experiments" to "Culture and identify 10 different microorganisms.","Antibiotic Study" to "Study antibiotic resistance patterns of common bacteria.","Research Paper" to "Write paper on a microbiological topic.")),
            B(676,"B.Sc Zoology studies animal biology, ecology, wildlife, marine biology and evolutionary biology. Strong foundation for wildlife conservation, ecology research, IFS and zoo management careers.","Pursue M.Sc Zoology then PhD. Crack IFS Indian Forest Service exam for wildlife officer roles. Work with WWF, WCS, Wildlife Institute of India, ZSI Kolkata or national parks.",listOf("Animal Anatomy & Physiology","Ecology & Wildlife Biology","Marine Biology","Evolutionary Biology","Animal Behaviour","Conservation Biology"),listOf("CSIR NET Life Sciences" to "CSIR India","IFS Exam" to "UPSC India","Wildlife Institute Training" to "WII Dehradun"),listOf("Wildlife Survey" to "Conduct bird count survey in a local green area.","Ecological Study" to "Study biodiversity in a local ecosystem.","Research Report" to "Write research report on a local wildlife species.")),
            B(677,"B.Sc Botany studies plant biology, plant physiology, ecology, plant genetics and plant biotechnology. Good foundation for agricultural research, environmental science and pharmaceutical botany careers.","Pursue M.Sc Botany then PhD. Work with botanical gardens, forest departments, pharmaceutical companies or agricultural research institutes like ICAR. CSIR NET for research fellowships.",listOf("Plant Anatomy & Morphology","Plant Physiology","Plant Ecology","Plant Genetics","Ethnobotany","Plant Biotechnology"),listOf("CSIR NET Botany" to "CSIR India","GATE Life Sciences" to "IIT/IISC","Forest Guard Exam" to "State Forest Dept"),listOf("Plant Collection" to "Collect and identify 50 plant specimens for herbarium.","Germination Study" to "Conduct germination experiments with different plant species.","Forest Survey" to "Survey plant diversity in a local forest or park.")),
            B(680,"B.Sc Physics studies mechanics, optics, quantum physics, thermodynamics and electromagnetism. Opens careers in research, ISRO, DRDO, data science, teaching and actuarial science. Top colleges: IIT, NIT, Delhi University, Banaras Hindu University.","Pursue M.Sc Physics from IIT, IISC, IISERs then PhD. Crack JEST, JAM for top programmes. Switch to data science, finance or engineering after BSc Physics. CSIR JRF for research fellowship.",listOf("Classical Mechanics","Electromagnetism","Quantum Physics","Thermodynamics","Optics","Mathematical Physics"),listOf("CSIR NET Physics" to "CSIR India","JEST Exam" to "DAE India","IIT JAM Physics" to "IIT/IISC"),listOf("Telescope Build" to "Build a simple refracting telescope.","Physics Experiments" to "Demonstrate 10 physics phenomena with home materials.","Data Analysis" to "Analyse physics experiment data using Python.")),
            B(681,"B.Sc Chemistry studies organic, inorganic and physical chemistry, chemical analysis and industrial chemistry. Careers in pharma, petrochemicals, materials science, food science and teaching. Top colleges: IIT, NIT, Miranda House Delhi, St Stephen's Delhi.","Pursue M.Sc Chemistry from IIT, IISc, NIT then PhD. Work at CSIR labs, Dr Reddy's, Cipla, ONGC, HPCL or teach in colleges. GATE Chemistry for IIT M.Sc admission.",listOf("Organic Chemistry","Inorganic Chemistry","Analytical Chemistry","Physical Chemistry","Spectroscopy","Industrial Chemistry"),listOf("CSIR NET Chemistry" to "CSIR India","GATE Chemistry" to "IIT/IISC","IIT JAM Chemistry" to "IIT India"),listOf("Synthesis Experiment" to "Perform organic synthesis experiments in lab.","Water Quality Test" to "Test local water samples for chemical contamination.","Titration" to "Perform acid-base titrations and calculate molarity.")),
            B(682,"B.Sc Mathematics studies calculus, algebra, statistics, number theory and differential equations. Opens careers in data science, finance, actuarial science, cryptography, teaching and research. Top colleges: IIT, IISC, CMI Chennai, ISI Kolkata.","Pursue M.Sc Mathematics from top universities then PhD. Crack CSIR NET for research fellowship. High demand in banking, insurance, IT and data science. IIT JAM for M.Sc admission.",listOf("Calculus & Real Analysis","Linear Algebra","Abstract Algebra","Statistics & Probability","Number Theory","Differential Equations"),listOf("CSIR NET Mathematics" to "CSIR India","IIT JAM Mathematics" to "IIT/IISC","Actuarial Exam" to "IAI India"),listOf("Census Analysis" to "Analyse real census data and find patterns.","Cryptography" to "Implement RSA encryption algorithm in Python.","Mathematical Visualisation" to "Create 3D mathematical visualisations using Python.")),
            B(683,"B.Sc Statistics studies probability theory, statistical inference, regression, time series and data analysis. Directly applicable to data science, finance, insurance, research and government statistics.","Pursue M.Sc Statistics from ISI Kolkata, Chennai Mathematical Institute, IIT, Delhi School of Economics. Work at RBI, CSO, NSSO, actuarial firms, banks, IT analytics companies.",listOf("Probability Theory","Statistical Inference","Regression Analysis","Time Series","Sampling Methods","Statistical Computing R/Python"),listOf("ISI Entrance" to "Indian Statistical Institute","CSIR NET Statistics" to "CSIR India","IIT JAM Statistics" to "IIT India"),listOf("Data Analysis Project" to "Analyse a real government dataset and present findings.","Statistical Model" to "Build regression model to predict a real-world outcome.","Survey Design" to "Design and analyse a stratified random sample survey.")),
            B(684,"B.Sc Computer Science is a 3-year degree covering programming, data structures, algorithms, databases, operating systems and networking. Pathway to software development and IT careers. Top colleges: IIT, NIT, BITS, Delhi University.","Pursue MCA or M.Sc CS from top institutes. Start internships from Year 1. Build GitHub portfolio. Target IT companies like TCS, Infosys, Wipro or product companies.",listOf("Data Structures & Algorithms","Programming Java/Python/C++","Operating Systems","Database Management","Computer Networks","Software Engineering"),listOf("GATE CS" to "IIT/IISC","Oracle Java Cert" to "Oracle","AWS Cloud Practitioner" to "Amazon"),listOf("Portfolio Project" to "Build 3 complete software projects for GitHub portfolio.","Competitive Coding" to "Solve 200 problems on LeetCode or HackerRank.","Open Source" to "Contribute to an open source project on GitHub.")),
            B(685,"B.Sc IT is a 3-year degree focused on information technology, web development, networking, cybersecurity and database administration. More industry-focused than B.Sc CS. Good placement at IT service companies.","Target TCS, Infosys, Wipro, Cognizant through campus placements. Pursue MCA or cloud certifications. Web development skills open freelance opportunities.",listOf("Web Technologies HTML/CSS/JS","Database Administration","Networking Fundamentals","Cybersecurity Basics","Software Testing","Project Management"),listOf("AWS Cloud Practitioner" to "Amazon","CompTIA Network+" to "CompTIA","Microsoft Azure Fundamentals" to "Microsoft"),listOf("Web Application" to "Build a complete web application with frontend and backend.","Network Setup" to "Set up a small office network with routing and switching.","Security Audit" to "Perform basic security audit of a web application.")),
            B(686,"B.Sc Data Science is a 3-year degree combining statistics, programming, machine learning and big data. One of the most in-demand degrees today with excellent salary prospects. Top colleges: Christ University, NMIMS, Symbiosis, VIT.","Build Kaggle competition wins. Pursue M.Sc Data Science or directly join analytics companies. Target firms like Mu Sigma, Fractal Analytics, Tiger Analytics or FAANG.",listOf("Python & R Programming","Statistics & Probability","Machine Learning","Data Visualisation","Big Data Tools","SQL & NoSQL Databases"),listOf("IBM Data Science Cert" to "Coursera/IBM","Google Data Analytics" to "Google/Coursera","Kaggle Competitions" to "Kaggle"),listOf("Kaggle Project" to "Complete a Kaggle competition and finish in top 30 percent.","ML Pipeline" to "Build end-to-end ML pipeline from data to deployment.","Dashboard" to "Create interactive data dashboard using Python Dash.")),
            B(687,"B.Sc Environmental Science studies ecology, pollution, conservation, environmental policy and sustainable development. Relevant for careers in environmental consulting, government agencies and international organisations.","Pursue M.Sc Environmental Science from JNU, Teri University, Manipal, Pune University. Work with MoEFCC, SPCBs, TERI, WWF, UNEP. Good scope in corporate sustainability roles.",listOf("Environmental Chemistry","Ecology & Biodiversity","GIS & Remote Sensing","Environmental Impact Assessment","Pollution Monitoring","Environmental Law"),listOf("CSIR NET Environment" to "CSIR India","EIA Consultant Cert" to "MoEFCC","ISO 14001 Auditor" to "BSI Group"),listOf("EIA Report" to "Prepare Environmental Impact Assessment for a hypothetical project.","Water Study" to "Monitor water quality of a local water body.","Carbon Audit" to "Calculate carbon footprint of your college campus.")),
            B(688,"B.Sc Nutrition and Dietetics studies food science, human nutrition, clinical dietetics and community health. Growing demand as India focuses on preventive health, fitness and lifestyle disease management.","Pursue M.Sc Dietetics then register with Indian Dietetic Association. Work in hospitals, fitness centres, corporate wellness, sports nutrition or start own nutrition consultancy.",listOf("Human Nutrition","Clinical Dietetics","Food Science","Community Nutrition","Sports Nutrition","Nutrition Counselling"),
            listOf("National Dietetic Association" to "NDA India","RD Exam" to "Indian Dietetic Association","Sports Nutrition Cert" to "ISSA India"),
            listOf("Diet Plan" to "Create customised diet plans for 10 clients with different goals.","Nutrition Research" to "Conduct dietary assessment study in your college.","Healthy Cooking" to "Create a healthy recipe book with nutritional calculations.")),
            B(689,"B.Sc Forensic Science applies science to criminal investigation including DNA analysis, fingerprinting, toxicology, document examination and crime scene investigation. Growing career with increasing crime investigation needs.","Pursue M.Sc Forensic Science from Panjab University, GFSU Gujarat, Bundelkhand University. Work with CBI, State FSLs, police departments or private forensic labs.",listOf("Forensic Chemistry","DNA Analysis & Genetics","Fingerprint Analysis","Toxicology","Crime Scene Investigation","Legal Procedures"),listOf("M.Sc Forensic Entrance" to "University/GFSU","GFSU Certification" to "Gujarat Forensic Science Univ","UGC NET Forensic" to "NTA India"),listOf("Crime Scene Simulation" to "Create and process a mock crime scene with evidence.","Fingerprint Analysis" to "Collect and analyse fingerprints using forensic techniques.","Toxicology Report" to "Write a toxicological analysis report for a fictional case.")),
            B(690,"B.Sc Agriculture is a 4-year degree covering crop science, soil science, agronomy, horticulture and agricultural economics. Excellent scope in ICAR research, agribusiness, government agriculture services and agritech startups.","Clear ICAR AIEEA for admission or State Agricultural University entrance. Pursue M.Sc Agriculture then PhD. Target ICAR institutes, Mahindra Agri, Rallis, Syngenta, IFFCO, state agri departments.",listOf("Agronomy & Crop Science","Soil Science & Fertility","Plant Protection","Agricultural Economics","Horticulture","Farm Machinery"),listOf("ICAR AIEEA" to "ICAR India","GATE Agriculture" to "IIT/IISC","Agriculture Officer Exam" to "State PSC"),listOf("Field Experiment" to "Conduct crop yield experiment on a small plot of land.","Soil Testing" to "Test soil health parameters and prepare soil health card.","Agri Business Plan" to "Create a business plan for an agri-startup.")),
            // B.COM
            B(704,"CA is the most prestigious professional qualification in India for commerce graduates. CAs earn 7-40+ lakhs per annum working in Big 4 firms, corporations and as independent practitioners. Top recruiting firms: Deloitte, PwC, KPMG, EY.","Register with ICAI after B.Com. Clear CA Intermediate then CA Final. Complete 3 years articleship. Many B.Com students do articleship simultaneously with CA studies.",listOf("Financial Accounting","Taxation GST & Income Tax","Auditing & Assurance","Corporate Law","Cost Accounting","Financial Analysis"),listOf("CA Foundation" to "ICAI","CA Intermediate" to "ICAI","CA Final" to "ICAI"),listOf("GST Filing" to "Practice filing monthly GSTR-1 and GSTR-3B returns.","Tax Audit" to "Prepare mock Form 3CA/3CD for a small firm.","Financial Model" to "Build a DCF valuation model for a listed company.")),
            B(705,"CMA after B.Com focuses on cost accounting, management accounting and strategic finance. CMAs are vital in manufacturing, banking and government for cost control and financial management decisions.","Register with ICMAI. Strong demand in PSUs like BHEL, SAIL, ONGC, GAIL. Private manufacturing companies also recruit CMAs for cost management and financial planning roles.",listOf("Cost Accounting","Management Accounting","Financial Management","Strategic Management","Taxation","ERP Systems SAP/Oracle"),listOf("CMA Foundation" to "ICMAI","CMA Intermediate" to "ICMAI","CMA Final" to "ICMAI"),listOf("Cost Sheet" to "Prepare complete cost sheet for a manufacturing unit.","Budget Variance" to "Analyse budget vs actual variance for a company.","Transfer Pricing" to "Study and document transfer pricing regulations.")),
            B(706,"M.Com is a 2-year postgraduate degree enhancing commerce knowledge in accounting, finance, business law and economics. Gateway to PhD, lectureship, banking and corporate finance roles.","Pursue M.Com from Delhi University, Mumbai University, Calcutta University, Osmania University. Clear UGC NET Commerce for Assistant Professor positions. Salary 6-20 LPA.",listOf("Advanced Financial Accounting","Corporate Taxation","Business Statistics","Financial Markets","Research Methodology","International Business"),listOf("M.Com Entrance" to "University","UGC NET Commerce" to "NTA India","MBA Entrance CAT" to "IIM"),listOf("Research Paper" to "Write research paper on commerce topic for journal publication.","Case Analysis" to "Analyse financial performance of a company over 5 years.","Tax Project" to "Complete GST and Income Tax filing simulation.")),
            B(707,"Income Tax Practitioner ITP is a government-authorised professional who files income tax returns for individuals and businesses. Growing demand as India formalises its tax ecosystem.","Register as ITP with the Income Tax Department. Clear ITP exam. Build client base for tax filing, GST compliance and financial advisory. Excellent for semi-urban and rural areas.",listOf("Income Tax Act Provisions","GST Compliance","TDS Filing","Financial Statement Preparation","Tax Planning","Client Advisory"),listOf("ITP Registration" to "Income Tax Dept India","Tally GST Cert" to "Tally Solutions","GST Practitioner" to "GSTN India"),listOf("ITR Filing" to "File income tax returns for 10 different client profiles.","GST Return" to "Practice filing GSTR-1, 3B and 9 returns.","Tax Planning" to "Create a complete tax planning strategy for a salaried individual.")),
            B(708,"MBA Finance after B.Com is a powerful combination for corporate finance, investment banking, financial planning and analysis. IIM MBA Finance graduates earn 20-60 LPA packages.","Crack CAT, XAT or GMAT for top B-schools. Target IIM-A, IIM-B, IIM-C, XLRI, ISB, MDI. Work experience before MBA increases packages significantly.",listOf("Corporate Finance","Investment Analysis","Financial Modelling","Risk Management","Portfolio Management","Derivatives & Structured Products"),listOf("CFA Level 1" to "CFA Institute","FRM Exam" to "GARP","Bloomberg Certification" to "Bloomberg"),listOf("Stock Portfolio" to "Manage a virtual stock portfolio of 1 lakh for 3 months.","Company Valuation" to "Do DCF and comparable company valuation analysis.","Market Analysis" to "Write weekly analysis of stock market movements for 1 month.")),
            B(709,"Bank PO after B.Com is highly natural fit as commerce background covers banking awareness, financial markets and accounting already. Salary 8-15 LPA with promotions to Branch Manager and above.","Clear IBPS PO or SBI PO exam. Practice quantitative aptitude, reasoning and English daily. Commerce graduates have advantage in banking awareness section.",listOf("Quantitative Aptitude","Logical Reasoning","English Language","Banking & Financial Awareness","Computer Basics","Current Affairs"),listOf("IBPS PO" to "IBPS India","SBI PO" to "SBI India","RBI Grade B" to "RBI India"),listOf("Mock Tests" to "Solve 60 full-length bank exam mock tests.","Current Affairs" to "Maintain daily banking news notes for 6 months.","Speed Maths" to "Practice 100 aptitude questions daily for accuracy.")),
            B(713,"CS Company Secretary is a legal and compliance expert managing corporate governance, board meetings, secretarial practices and regulatory filings. Required by all listed companies under Companies Act 2013.","Register with ICSI. Clear Foundation then Executive then Professional programme. Top employers: Tata, Reliance, Infosys, HDFC Bank, government enterprises. Salary 6-25 LPA.",listOf("Company Law","Securities Law & SEBI Regulations","Secretarial Practice","Corporate Governance","Drafting & Conveyancing","FEMA & International Law"),listOf("CS Foundation" to "ICSI India","CS Executive" to "ICSI India","CS Professional" to "ICSI India"),listOf("Board Minutes" to "Draft board meeting minutes and resolutions.","ROC Filing" to "Practice filing annual returns with MCA.","Compliance Calendar" to "Create annual compliance calendar for a listed company.")),
            B(714,"CFA Chartered Financial Analyst is the world's most respected investment qualification. CFA charterholders work as portfolio managers, equity researchers and investment analysts at top financial firms globally.","Study for CFA Level 1, 2 and 3 exams. Minimum 4000 hours study time required. Top employers: Motilal Oswal, Kotak Securities, HDFC AMC, Axis Mutual Fund, Morgan Stanley India.",listOf("Ethical & Professional Standards","Portfolio Management","Equity Valuation","Fixed Income","Derivatives","Alternative Investments"),listOf("CFA Level 1" to "CFA Institute USA","CFA Level 2" to "CFA Institute USA","CFA Level 3" to "CFA Institute USA"),listOf("Stock Analysis" to "Write CFA-style equity research report on 3 stocks.","Model Portfolio" to "Build and manage model investment portfolio for 6 months.","Ethics Case" to "Analyse 10 CFA ethics violation case studies.")),
            B(715,"Actuarial Science uses advanced mathematics and statistics to price insurance products, manage pension funds and assess financial risk. One of India's highest paying and most intellectually demanding careers.","Clear IAI exams. 9 CT papers take 3-7 years. Top employers: Life Insurance Corporation, Max Life, ICICI Prudential, Mercer, Towers Watson, Aon Hewitt. Salary 15-60+ LPA.",listOf("Probability & Statistics","Financial Mathematics","Life Contingencies","Insurance Mathematics","Risk Modelling","Actuarial Software"),
            listOf("IAI CT Exams" to "Institute of Actuaries India","IFoA Fellowship" to "IFoA UK","CFA Level 1" to "CFA Institute"),
            listOf("Mortality Study" to "Build a complete life table from population mortality data.","Insurance Pricing" to "Calculate premium for term and endowment life insurance products.","Risk Model" to "Build a stochastic risk model for a life insurance portfolio.")),
            // STRUCTURAL ENGINEER and other BTech Civil
            B(730,"Structural Engineers design the load-bearing elements of buildings, bridges, dams, towers and industrial structures ensuring safety and stability. High demand with India's infrastructure boom.","Work with construction giants L&T, Shapoorji Pallonji, AECOM, Arup or government agencies like CPWD, PWD, NHAI. Pursue M.Tech Structural Engineering from IIT for specialisation.",listOf("Structural Analysis","Reinforced Concrete Design","Steel Structure Design","Foundation Engineering","Finite Element Analysis","AutoCAD & Revit"),listOf("GATE Civil" to "IIT/IISC","SE Certification" to "Institution of Engineers India","PMP" to "PMI"),listOf("RCC Design" to "Design and calculate a complete RCC framed structure.","Bridge Model" to "Design a steel truss bridge for given loading conditions.","FEA Analysis" to "Perform finite element analysis using STAAD Pro or ETABS.")),
            B(731,"Transportation Engineers design and improve road networks, highways, railways, airports and public transit systems. Critical for India's massive infrastructure development programme.","Work with NHAI, MoRTH, DMRC, state PWD or private engineering consultancies. Target AECOM, Atkins, Jacobs Engineering. Pursue M.Tech Transportation Engineering from IIT.",listOf("Highway Engineering","Traffic Engineering","Transportation Planning","GIS & AutoCAD","Environmental Assessment","Project Management"),
            listOf("GATE Civil" to "IIT/IISC","Highway Engineer Cert" to "IRC India","PMP" to "PMI"),
            listOf("Traffic Study" to "Conduct traffic volume count and analysis at busy intersection.","Highway Design" to "Design a 2-lane rural highway for a 5km stretch.","Transit Plan" to "Propose a public transit improvement plan for your city.")),
            B(732,"Geotechnical Engineers study soil and rock mechanics to design foundations, retaining walls, tunnels, embankments and earthworks. Essential for all civil infrastructure projects.","Work on metro projects, dam construction, offshore platforms and building foundations. RITES, IRCON, NHPC recruit geotechnical engineers. Pursue M.Tech Geotechnical from IIT.",listOf("Soil Mechanics","Rock Mechanics","Foundation Design","Slope Stability Analysis","PLAXIS & STAAD","Site Investigation"),
            listOf("GATE Civil" to "IIT/IISC","Geotechnical Cert" to "IGS India","PMP" to "PMI"),
            listOf("Soil Testing" to "Perform complete soil investigation including SPT and triaxial tests.","Foundation Design" to "Design shallow and deep foundation for a given soil profile.","Slope Stability" to "Analyse slope stability for a hillside construction site.")),
            B(733,"Water Resources Engineers design irrigation systems, dams, canals, water treatment plants and flood control structures. Critical for India's water security and agricultural development.","Work with Central Water Commission, state irrigation departments, NHPC, WAPCOS or international organisations like World Bank funded projects. M.Tech Water Resources from IIT.",listOf("Hydrology & Water Resources","Hydraulics","Irrigation Engineering","Dam Design","GIS & Remote Sensing","Environmental Impact Assessment"),
            listOf("GATE Civil" to "IIT/IISC","CWC Recruitment" to "Central Water Commission","State Irrigation Exam" to "State PSC"),
            listOf("Dam Design" to "Design a small earthen dam for a hypothetical watershed.","Irrigation Layout" to "Design a drip irrigation system for a 10-acre farm.","Flood Analysis" to "Perform hydrological flood frequency analysis for a river.")),
            B(734,"Construction Project Managers oversee entire building projects from planning to completion managing contractors, materials, schedule, budget and quality on large infrastructure projects.","Work with L&T, Gammon India, Shapoorji Pallonji, NCC, TATA Projects or real estate developers DLF, Godrej Properties, Prestige. PMP certification essential for senior roles.",listOf("Construction Planning & Scheduling","Cost Estimation","Contract Management","Risk Management","MS Project & Primavera","Safety Management"),
            listOf("PMP Certification" to "PMI","CIOB Membership" to "Chartered Institute of Building UK","RICS Membership" to "Royal Institution of Chartered Surveyors"),
            listOf("Project Schedule" to "Create complete MS Project schedule for a construction project.","Cost Estimate" to "Prepare detailed cost estimate for a commercial building.","Risk Register" to "Identify and quantify risks for a large infrastructure project.")),
            B(735,"Urban and Town Planners design sustainable cities, manage land use, create development plans and solve problems like traffic congestion, housing shortage and environmental degradation.","Work with Urban Development Authorities, Smart City Mission, DUDA, PMAY or private urban planning consultancies. RERA gives excellent scope for town planning professionals.",listOf("Urban Planning Theory","Master Plan Preparation","GIS & Remote Sensing","Land Use Regulation","Traffic & Transport Planning","Environmental Impact Assessment"),listOf("GATE Urban Planning" to "IIT/IISC","Town Planner Cert" to "ITPI India","LEED Green Associate" to "USGBC"),listOf("Sector Plan" to "Prepare a sector-level development plan for a urban area.","GIS Analysis" to "Create GIS-based spatial analysis of urban growth patterns.","Housing Study" to "Analyse affordable housing needs and propose policy solutions."))
        )
        for (b in list) {
            dao.insertDetail(CareerDetailEntity(nodeId = b.id, description = b.desc, suggestedNextStep = b.next))
            dao.insertSkills(b.skills.map { SkillEntity(nodeId = b.id, skillName = it) })
            dao.insertCertifications(b.certs.map { (n, p) -> CertificationEntity(nodeId = b.id, certName = n, provider = p) })
            dao.insertProjects(b.projs.map { (n, d) -> ProjectEntity(nodeId = b.id, projectName = n, description = d) })
        }
    }
}
