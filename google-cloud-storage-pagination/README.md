# Problem Statement
Suppose you have the following storage structure in a bucket
gcs_bucket/${unique_id}/${user_id}/${timestamp}.txt
for a given ${unique_id} and ${user_id}, there can be multiple
timestamp based files.
How would you implement pagination when someone requests data for
a ${unique_id} and ${user_id}.


