module.exports = {
    branches: ["main"],
    plugins: [
        '@semantic-release/commit-analyzer',
        '@semantic-release/release-notes-generator',
        '@semantic-release/changelog',
        [
        '@semantic-release/exec',
        {
            prepareCmd: 'echo "${nextRelease.version}" > release_version.txt'
        }
        ],
        [
            '@semantic-release/git',
            {
                assets: ['CHANGELOG.md'],
                message: 'chore(release): ${nextRelease.version} [skip ci]'
            }
        ],
        [
            '@semantic-release/gitlab',
            {
                gitlabUrl: 'https://gitlab.hof-university.de'
            }
        ]
    ]

};