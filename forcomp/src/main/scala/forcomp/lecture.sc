type UserId = Long

case class CandidateMetadata(
  userIds: Seq[UserId],
  numberOfGoodUsers: Int,
  numberOfBadUsers: Int)

case class ProfileGeoCandidate(
  biolocation: String,
  metadata: CandidateMetadata,
  geoLocationId: String)

def saveGoodCandidate(candidate: ProfileGeoCandidate, userId: UserId): Stitch[Unit] = {

}

def incrementUserCountsForCandidates()

def getCandidatesForLocation(biolocation: String): Stitch[Seq[ProfileGeoCandidate]] = ???

def saveCandidate(candidate: ProfileGeoCandidate): Stitch[Unit] = ???
type UserId = Long

case class CandidateMetadata(
  userIds: Seq[UserId],
  numberOfGoodUsers: Int,
  numberOfBadUsers: Int)

case class ProfileGeoCandidate(
  biolocation: String,
  metadata: CandidateMetadata,
  geoLocationId: String)

def saveGoodCandidate(candidate: ProfileGeoCandidate, userId: UserId): Stitch[Unit] = {

}

def incrementUserCountsForCandidates()

def getCandidatesForLocation(biolocation: String): Stitch[Seq[ProfileGeoCandidate]] = ???

def saveCandidate(candidate: ProfileGeoCandidate): Stitch[Unit] = ???
type UserId = Long

case class CandidateMetadata(
  userIds: Seq[UserId],
  numberOfGoodUsers: Int,
  numberOfBadUsers: Int)

case class ProfileGeoCandidate(
  biolocation: String,
  metadata: CandidateMetadata,
  geoLocationId: String)

def saveGoodCandidate(candidate: ProfileGeoCandidate, userId: UserId): Stitch[Unit] = {

}

def incrementUserCountsForCandidates()

def getCandidatesForLocation(biolocation: String): Stitch[Seq[ProfileGeoCandidate]] = ???

def saveCandidate(candidate: ProfileGeoCandidate): Stitch[Unit] = ???
